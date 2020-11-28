package app.usersapp.page.users

import androidx.paging.PageKeyedDataSource
import app.domain.common.ResultState
import app.domain.entity.UserEntity
import app.domain.useCase.user.UserUseCase
import app.usersapp.mapper.toUserData
import app.usersapp.modal.UserData
import app.usersapp.utils.interfaces.Callback
import kotlinx.coroutines.*

class UserDataSource(
    private val userCase: UserUseCase,
    private val scope: CoroutineScope,
    private val apiCallback: Callback? = null
) : PageKeyedDataSource<Int, UserData>() {
    var apiCall: Job? = null
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserData>
    ) {
        apiCall?.cancel()
        apiCall = scope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                userCase.getUsers(1)
            }.let {
                when (it) {
                    is ResultState.Success -> {
                        withContext(Dispatchers.Main) {
                            callback.onResult(it.data?.data?.map { map -> map.toUserData() }
                                ?: emptyList(), 0, it.data?.meta?.pagination?.total ?: 0, null, 1)
                        }
                    }
                    is ResultState.Error -> {
                        apiCallback?.onUpdate(it.exception.message ?: "")
                    }
                }
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserData>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserData>
    ) {
        apiCall?.cancel()
        apiCall = scope.launch(Dispatchers.IO) {
            userCase.getUsers(params.key).let {
                when (it) {
                    is ResultState.Success -> {
                        withContext(Dispatchers.Main) {
                            callback.onResult(it.data?.data?.map { map -> map.toUserData() }
                                ?: emptyList(), params.key + 1)
                        }
                    }
                    is ResultState.Error -> {
                        apiCallback?.onUpdate(it.exception.message ?: "")
                    }
                }
            }
        }
    }
}
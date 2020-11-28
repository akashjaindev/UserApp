package app.usersapp.page.users

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import app.domain.useCase.user.UserUseCase
import app.usersapp.modal.UserData
import app.usersapp.utils.interfaces.Callback
import kotlinx.coroutines.CoroutineScope

class UserNetworkDataSourceFactory(
    private val userCase: UserUseCase,
    private val scope: CoroutineScope,
    private val callback: Callback?=null
) : DataSource.Factory<Int, UserData>() {
    val dataLiveData = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, UserData> {
        return UserDataSource(userCase, scope, callback).also {
            dataLiveData.postValue(it)
        }
    }
}
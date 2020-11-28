package app.usersapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import app.domain.useCase.user.UserUseCase
import app.usersapp.modal.UserData
import app.usersapp.page.users.UserNetworkDataSourceFactory
import app.usersapp.ui.base.ActivityBaseViewModel
import app.usersapp.utils.interfaces.Callback
import javax.inject.Inject

class MainViewModel @Inject constructor(userCase: UserUseCase) : ActivityBaseViewModel() {
    val userListData: LiveData<PagedList<UserData>>
    val adapter=UserAdapter(this)
    var factory:UserNetworkDataSourceFactory?=null
    init {
        userListData=UserNetworkDataSourceFactory(userCase, viewModelScope,
                object : Callback {
                    override fun onUpdate(value: String) {
                        showMessage.postValue(value)
                    }
                }).apply {
            factory=this
        }.toLiveData(PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20 * 2)
                .setEnablePlaceholders(false)
                .build())
    }

    fun refreshUsers() {
        factory?.dataLiveData?.value?.invalidate()
    }

    fun showData(data:UserData){
        showMessage.value="${data.name} \n ${data.email}"
    }
}
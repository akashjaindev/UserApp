package app.usersapp.ui.base

import androidx.lifecycle.ViewModel
import app.usersapp.utils.SingleLiveEvent

abstract class ActivityBaseViewModel : ViewModel() {
    val showMessage = SingleLiveEvent<String>()
    val loadingLiveData = SingleLiveEvent<Boolean>()
}
package app.usersapp.modal

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class UserData : BaseObservable() {
    @get:Bindable
    var id: Int? = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var name: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var email: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var male: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.male)
        }

    @get:Bindable
    var status: Boolean? = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.status)
        }
}
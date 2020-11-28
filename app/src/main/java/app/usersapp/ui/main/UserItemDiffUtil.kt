package app.usersapp.ui.main

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import app.usersapp.modal.UserData

class UserItemDiffUtil : DiffUtil.ItemCallback<UserData>() {
    override fun areItemsTheSame(
        oldItem: UserData,
        newItem: UserData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UserData,
        newItem: UserData
    ): Boolean {
        return TextUtils.equals(oldItem.name, newItem.name)
                && TextUtils.equals(oldItem.email, newItem.email)
                && oldItem.status == newItem.status
                && oldItem.male == newItem.male
    }
}
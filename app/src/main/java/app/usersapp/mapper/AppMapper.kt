package app.usersapp.mapper

import app.domain.entity.UserEntity
import app.usersapp.modal.UserData

fun UserEntity.UserItemData.toUserData() = UserData().also {
    it.id = id
    it.name = name
    it.email = email
    it.male = gender?.equals("Male", true) ?: false
    it.status = status?.equals("Active", true) ?: false
}
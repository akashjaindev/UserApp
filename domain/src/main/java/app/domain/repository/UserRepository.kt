package app.domain.repository

import app.domain.common.ResultState
import app.domain.entity.UserEntity

interface UserRepository {
    suspend fun getUsers(page: Int): ResultState<UserEntity.UserData>?
}
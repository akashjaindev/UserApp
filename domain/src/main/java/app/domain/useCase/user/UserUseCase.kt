package app.domain.useCase.user

import app.domain.common.ResultState
import app.domain.entity.UserEntity

interface UserUseCase {
    suspend fun getUsers(page: Int): ResultState<UserEntity.UserData>?
}
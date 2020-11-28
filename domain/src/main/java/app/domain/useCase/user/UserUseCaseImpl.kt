package app.domain.useCase.user

import app.domain.common.ResultState
import app.domain.entity.UserEntity
import app.domain.repository.UserRepository

class UserUseCaseImpl(private val repository: UserRepository) : UserUseCase {
    override suspend fun getUsers(page: Int): ResultState<UserEntity.UserData>? {
        return repository.getUsers(page)
    }
}
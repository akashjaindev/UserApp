package app.usersapp.di.module

import app.domain.repository.UserRepository
import app.domain.useCase.user.UserUseCase
import app.domain.useCase.user.UserUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideUserUseCaseImpl(repository: UserRepository): UserUseCase =
        UserUseCaseImpl(repository)
}
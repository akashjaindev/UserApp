package app.usersapp.di.module

import android.content.SharedPreferences
import app.data.api.IUserApi
import app.data.repository.user.UserRepositoryImpl
import app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class RepositoryModule {
    @Provides
    fun provideAuthRepository(api: IUserApi, sharedPreferences: SharedPreferences): UserRepository {
        return UserRepositoryImpl(api, sharedPreferences)
    }
}

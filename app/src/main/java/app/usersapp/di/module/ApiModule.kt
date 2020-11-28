package app.usersapp.di.module

import app.data.api.IUserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): IUserApi {
        return retrofit.create(IUserApi::class.java)
    }
}
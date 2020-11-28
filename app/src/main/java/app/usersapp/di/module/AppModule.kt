package app.usersapp.di.module

import android.content.Context
import android.content.SharedPreferences
import app.usersapp.AppApplication
import app.usersapp.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * provides application level instances
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: AppApplication): Context {
        return application.baseContext
    }

    @Provides
    @Singleton
    fun sharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }
}
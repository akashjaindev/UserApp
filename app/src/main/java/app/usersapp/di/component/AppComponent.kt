package app.usersapp.di.component

import app.usersapp.AppApplication
import app.usersapp.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *  initialize modules used for Dagger to provide instance to application
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<AppApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<AppApplication>
}
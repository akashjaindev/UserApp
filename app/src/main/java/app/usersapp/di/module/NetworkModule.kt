package app.usersapp.di.module

import app.usersapp.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    /*
    * The method returns the Retrofit object
    * */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun okHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor/*,
        sharedPreferences: SharedPreferences*/
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            /*.addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .header(
                            "Authorization",
                            sharedPreferences.getPreference(SharedPreferenceKeys.PREF_TOKEN, "")
                                .let { token ->
                                    if (TextUtils.isEmpty(token))
                                        Credentials.basic("admin", "12345678")
                                    else "Bearer $token"
                                }.toString()
                        )
                        .method(it.request().method, it.request().body).build()
                )
            }*/.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
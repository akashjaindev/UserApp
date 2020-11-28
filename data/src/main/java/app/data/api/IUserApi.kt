package app.data.api

import app.domain.entity.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IUserApi {

    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<UserEntity.UserData>
}
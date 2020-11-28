package app.data.repository.user

import android.content.SharedPreferences
import app.data.api.IUserApi
import app.data.exception.ApiException
import app.data.extension.onError
import app.data.extension.retryIO
import app.domain.common.ResultState
import app.domain.entity.UserEntity
import app.domain.repository.UserRepository
import retrofit2.await

class UserRepositoryImpl(
    private val api: IUserApi,
    private val sharedPreferences: SharedPreferences
) : UserRepository {
    override suspend fun getUsers(page: Int): ResultState<UserEntity.UserData>? {
        return try {
            retryIO(onError = { it.onError() }) {
                val apiResponse = api.getUsers(page).await()
                when (apiResponse.code) {
                    200 -> { // Phone Number verified
                        ResultState.Success(apiResponse)
                    }
                    else -> ApiException(apiResponse.code, "Error in Api").onError()
                }
            }
        } catch (e: Exception) {
            e.onError(true)
        }
    }
}
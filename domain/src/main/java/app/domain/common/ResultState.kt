package app.domain.common

/**
 * Created by Akash Jain
 */
/**
 * A wrapper for database and network states.
 */
sealed class ResultState<T> {

//    /**
//     * A state of [data] which shows that we know there is still an update to come.
//     */
//    data class Loading<T>(val data: T) : ResultState<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T?) : ResultState<T>()

    /**
     * A state to show a [exception] is thrown.
     */
    data class Error<T>(val exception: Exception) : ResultState<T>()
}
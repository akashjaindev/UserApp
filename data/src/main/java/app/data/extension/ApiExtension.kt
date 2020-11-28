package app.data.extension

import app.domain.common.ResultState
import kotlinx.coroutines.delay
import java.io.IOException

suspend fun <T> retryIO(
    times: Int = 3, //Int.MAX_VALUE,
    initialDelay: Long = 100, // 0.1 second
    maxDelay: Long = 1000,    // 1 second
    factor: Double = 2.0,
    onError: (e: Exception) -> T,
    block: suspend () -> T
): T {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return block()
        } catch (e: IOException) {
            e.printStackTrace()
            val value = onError(e)
            if (value != null)
                return value
            // you can log an error here and/or make a more finer-grained
            // analysis of the cause to see if retry is needed
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return block() // last attempt
}

fun <T> Exception.onError(lastCall: Boolean = false): ResultState<T> {
    return when {
        lastCall -> ResultState.Error(this)
//            e is UnknownHostException -> null
        else -> ResultState.Error(this)
    }
}
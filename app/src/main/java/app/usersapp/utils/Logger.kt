package app.usersapp.utils

import android.util.Log
import app.usersapp.BuildConfig


object Logger {
    private const val TAG = "Application"
    private var LOGGER: Boolean = BuildConfig.DEBUG

    fun error(s: String?, throwable: Throwable) {
        if (LOGGER)
            Log.e(TAG, s, throwable)
    }

    fun e(s: String) {
        if (LOGGER)
            Log.e(TAG, "" + s)
    }

    fun w(s: String) {
        if (LOGGER)
            Log.w(TAG, "" + s)
    }

    fun i(s: String) {
        Log.i(TAG, "" + s)
    }

    fun v(s: String) {
        if (LOGGER)
            Log.v(TAG, "" + s)
    }

    fun d(s: String) {
        if (LOGGER)
            Log.d(TAG, "" + s)
    }

    fun w(string: String, e: Exception) {
        if (LOGGER)
            Log.w(TAG, string, e)
    }

    fun e(string: String, e: Exception) {
        if (LOGGER)
            Log.e(TAG, string, e)
    }
}
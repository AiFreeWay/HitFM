package tech.intom.hitfm.application.utils

import android.util.Log
import tech.intom.hitfm.BuildConfig

/**
 * Created by root on 09.04.18.
 */
object Logger {

    val LOG_TAG = "Rusradio"
    var isShowLog = false

    fun log(message: String) {
        if (isShowLog && BuildConfig.DEBUG) {
            Log.d(LOG_TAG, message)
        }
    }

    fun logError(message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(LOG_TAG, message)
        }
    }

    fun logObjectCreating(loggedObject: Any) {
        if (isShowLog && BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "Created -- "+loggedObject::class.java.canonicalName)
        }
    }
}
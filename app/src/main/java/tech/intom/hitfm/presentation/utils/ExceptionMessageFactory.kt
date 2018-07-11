package tech.intom.hitfm.presentation.utils

import android.content.Context
import tech.intom.hitfm.R

/**
 * Created by root on 17.04.18.
 */
object ExceptionMessageFactory {

    fun getMessageByException(exception: Throwable, context: Context): String {
        return when (exception) {
            else -> context.getString(R.string.error)
        }
    }
}
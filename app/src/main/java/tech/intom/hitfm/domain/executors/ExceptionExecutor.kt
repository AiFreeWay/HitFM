package tech.intom.hitfm.domain.executors

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import tech.intom.hitfm.domain.models.ExceptionStoreModel
import tech.intom.hitfm.presentation.screens.main.MainActivityOld
import tech.intom.hitfm.data.repository.abstractions.ExceptionRepository
import tech.intom.hitfm.application.utils.Logger

/**
 * Created by root on 10.04.18.
 */
class ExceptionExecutor constructor(private val mRepository: ExceptionRepository,
                                            private val mContext: Context) {

    private val RESTART_INTENT_ID = ExceptionExecutor::class.java.simpleName.hashCode()
    private val MINUTE_EXCEPTION_INTERVAL = 5

    init {
        Logger.logObjectCreating(this)
    }

    fun isExceptionAllowed(exception: Throwable): Boolean {
        var isExceptionAllowed = true

        for (exceptionModel in mRepository.getExceptions()) {

            if (TextUtils.equals(exceptionModel.exceptionTag, exception::class.java.canonicalName)) {
                isExceptionAllowed = checkExceptionAllowed(exceptionModel)
                break
            }
        }

        updateExceptionStore(exception)

        return isExceptionAllowed
    }

    fun restartApp() {
        val restartPendingIntent = PendingIntent.getActivity(mContext,
                RESTART_INTENT_ID,
                Intent(mContext, MainActivityOld::class.java),
                PendingIntent.FLAG_CANCEL_CURRENT)

        val alarmManager = mContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+500, restartPendingIntent)
    }

    private fun checkExceptionAllowed(exceptionModel: ExceptionStoreModel): Boolean {
        val diffeenceinMinutes = (System.currentTimeMillis() - exceptionModel.crashDate) / (60*1000)

        return diffeenceinMinutes > MINUTE_EXCEPTION_INTERVAL
    }

    private fun updateExceptionStore(exception: Throwable) {
        val exceptionModel = ExceptionStoreModel(exception::class.java.canonicalName,
                System.currentTimeMillis())

        mRepository.putExceptions(exceptionModel)
    }
}
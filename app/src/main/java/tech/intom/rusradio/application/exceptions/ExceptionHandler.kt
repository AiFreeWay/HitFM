package tech.intom.rusradio.application.exceptions

import java.io.PrintWriter
import java.io.StringWriter
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.application.RusradioApp
import tech.intom.rusradio.domain.executors.ExceptionExecutor
import javax.inject.Inject

/**
 * Created by root on 10.04.18.
 */
class ExceptionHandler(private val mApplication: RusradioApp) : Thread.UncaughtExceptionHandler {

    @Inject lateinit var mExceptionExecutor: ExceptionExecutor

    companion object {

        private val STRING_WRITER: StringWriter = StringWriter()
        private val PRINT_WRITER : PrintWriter = PrintWriter(STRING_WRITER)

        fun handleException(exception: Throwable) {
            exception.printStackTrace(PRINT_WRITER)
            PRINT_WRITER.flush()
            Logger.logError(STRING_WRITER.toString())
        }
    }

    init {
        mApplication.getRootComponent().inject(this)

        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread?, e: Throwable) {
        val currentActivity = mApplication.getCurrentActivity()

        currentActivity?.finish()

        handleException(e)

        if (mExceptionExecutor.isExceptionAllowed(e)) {
            mExceptionExecutor.restartApp()
        }

        System.exit(-1)
    }
}
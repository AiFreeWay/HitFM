package tech.intom.rusradio.application

import android.app.Application
import android.support.v7.app.AppCompatActivity
import tech.intom.rusradio.application.di.components.RootComponent
import tech.intom.rusradio.application.di.modules.RootModule
import com.orhanobut.hawk.Hawk
import com.squareup.leakcanary.LeakCanary
import tech.intom.rusradio.BuildConfig
import tech.intom.rusradio.application.di.components.DaggerRootComponent
import tech.intom.rusradio.application.exceptions.ExceptionHandler

/**
 * Created by root on 09.04.18.
 */
class RusradioApp : Application() {

    private lateinit var mRootComponent: RootComponent
    private lateinit var mExceptionHandler: ExceptionHandler

    private var mCurrentActivity: AppCompatActivity? = null

    override fun onCreate() {
        super.onCreate()
        if (!initLeakCanary()) {
            return
        }

        initPreferenceStore()

        mRootComponent = DaggerRootComponent.builder()
                .rootModule(RootModule(this))
                .build()

        mExceptionHandler = ExceptionHandler(this)
    }

    fun setCurrentActivity(activity: AppCompatActivity) {
        mCurrentActivity = activity
    }

    fun getCurrentActivity() = mCurrentActivity

    private fun initLeakCanary(): Boolean {
        if (BuildConfig.DEBUG) {
            return true
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return false
        }

        LeakCanary.install(this)
        return true
    }

    private fun initPreferenceStore() {
        Hawk.init(this)
                .build()
    }

    fun getRootComponent(): RootComponent = mRootComponent
}
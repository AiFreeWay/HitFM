package tech.intom.hitfm.presentation.screens

import android.os.Bundle
import android.os.Handler
import tech.intom.hitfm.presentation.screens.abstractions.BaseActivity
import tech.intom.hitfm.presentation.utils.navigator.ActivityNavigator

/**
 * Created by root on 09.04.18.
 */
class SplashActivity : BaseActivity(), Runnable {

    private val mSplashHandler = Handler()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        startToMainRunnable(SPLASH_SCREEN_DELAY)
    }

    override fun run() {
        showMainScreen()
    }

    private fun showMainScreen() {
        ActivityNavigator.showMainScreen(this)
        finish()
    }

    private fun startToMainRunnable(delayMillis: Int) {
        mSplashHandler.removeCallbacks(this)
        mSplashHandler.postDelayed(this, delayMillis.toLong())
    }

    companion object {

        private const val SPLASH_SCREEN_DELAY = 1200
    }
}

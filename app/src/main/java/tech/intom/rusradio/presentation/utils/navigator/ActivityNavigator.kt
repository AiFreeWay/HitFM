package tech.intom.rusradio.presentation.utils.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import tech.intom.rusradio.presentation.screens.main.MainActivity

/**
 * Created by root on 09.04.18.
 */
object ActivityNavigator {

    fun showMainScreen(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }
}
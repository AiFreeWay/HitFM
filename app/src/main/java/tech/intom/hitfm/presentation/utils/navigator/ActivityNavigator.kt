package tech.intom.hitfm.presentation.utils.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import tech.intom.hitfm.presentation.screens.main.MainActivityOld

/**
 * Created by root on 09.04.18.
 */
object ActivityNavigator {

    fun showMainScreen(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity, MainActivityOld::class.java))
    }
}
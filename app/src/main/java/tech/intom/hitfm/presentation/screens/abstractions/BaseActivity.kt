package tech.intom.hitfm.presentation.screens.abstractions

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import tech.intom.hitfm.application.App

/**
 * Created by root on 16.04.18.
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).setCurrentActivity(this)
    }
}
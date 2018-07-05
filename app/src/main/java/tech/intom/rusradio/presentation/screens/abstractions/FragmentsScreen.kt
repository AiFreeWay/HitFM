package tech.intom.rusradio.presentation.screens.abstractions

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import tech.intom.rusradio.application.di.components.RootComponent

/**
 * Created by root on 09.04.18.
 */
interface FragmentsScreen {

    fun getActivity(): AppCompatActivity
    fun getSupportFragmentManager(): FragmentManager
    fun getContainerId(): Int
    fun getRootComponent(): RootComponent
}
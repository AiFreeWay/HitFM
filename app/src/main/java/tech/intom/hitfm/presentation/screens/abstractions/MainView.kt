package tech.intom.hitfm.presentation.screens.abstractions

import com.arellomobile.mvp.MvpView

/**
 * Created by root on 16.04.18.
 */
interface MainView : MvpView {

    fun setProgressState(state: Boolean)
    fun showErrorDialog(error: Throwable)
    fun selectBottomNavigationTab(tabId: Int)
}
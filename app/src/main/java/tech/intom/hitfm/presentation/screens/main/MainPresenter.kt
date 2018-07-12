package tech.intom.hitfm.presentation.screens.main

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.models.FragmentHistoryItem
import tech.intom.hitfm.presentation.utils.ExceptionMessageFactory
import tech.intom.hitfm.presentation.screens.abstractions.BasePresenter
import tech.intom.hitfm.presentation.screens.abstractions.MainView
import tech.intom.hitfm.presentation.utils.navigator.FragmentFactory
import java.util.*
import javax.inject.Inject

/**
 * Created by root on 16.04.18.
 */
@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    @Inject lateinit var mFragmentRouter: Router
    private val mFragmetsHistoryStack = LinkedList<FragmentHistoryItem>()

    init {
        Logger.logObjectCreating(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        mFragmentRouter.newRootScreen(FragmentFactory.RADIO_FRAGMENT_TAG)
    }

    fun putNavRecordToHistoryStack(navTabId: Int, screenKey: String) {
        mFragmetsHistoryStack.addLast(FragmentHistoryItem(screenKey, true, navTabId))
    }

    fun openNavFragment(navTabId: Int, screenKey: String) {
        if (mFragmetsHistoryStack.last.navItemId != navTabId) {
            putNavRecordToHistoryStack(navTabId, screenKey)
            mFragmentRouter.navigateTo(screenKey)
        }
    }

    fun openFragment(screenKey: String) {
        if (mFragmetsHistoryStack.last.screenKey != screenKey) {
            val fragmentHistoryRecord = FragmentHistoryItem
                    .createNotNavFragmentHistoryItem(screenKey)

            mFragmetsHistoryStack.addLast(fragmentHistoryRecord)
            mFragmentRouter.navigateTo(screenKey)
        }
    }

    fun backNavigate() {
        mFragmentRouter.exit()

        if (mFragmetsHistoryStack.size>1) {
            val lastItem = mFragmetsHistoryStack[mFragmetsHistoryStack.size - 2]
            mFragmetsHistoryStack.removeLast()

            if (lastItem.isNavItem) {
                viewState.selectBottomNavigationTab(lastItem.navItemId)
            }
        }

    }

    fun getMessageByException(exception: Throwable, context: Context): String {
        return ExceptionMessageFactory.getMessageByException(exception, context)
    }
}
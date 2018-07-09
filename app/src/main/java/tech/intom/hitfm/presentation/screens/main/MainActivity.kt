package tech.intom.hitfm.presentation.screens.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import tech.intom.hitfm.R
import tech.intom.hitfm.application.RusradioApp
import tech.intom.hitfm.application.di.components.DaggerFragmentScreenComponent
import tech.intom.hitfm.application.di.components.RootComponent
import tech.intom.hitfm.application.di.modules.FragmentScreenModule
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.screens.abstractions.BaseActivity
import tech.intom.hitfm.presentation.screens.abstractions.FragmentsScreen
import tech.intom.hitfm.presentation.screens.abstractions.MainView

class MainActivity : BaseActivity(), MainView, FragmentsScreen {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main_old)

        Logger.logObjectCreating(this)
        setContentView(R.layout.ac_main_old)


        initToolbar()
        createComponent()

        ac_main_tv_menu_update.setOnClickListener({
            openCloseSideMenu()
            mUpdateMenuItemDelegat?.invoke()
        })

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onBackPressed() {
        mPresenter.backNavigate()
    }

    override fun setProgressState(state: Boolean) {
        ac_main_progress.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun showErrorDialog(error: Throwable) {
        AlertDialog.Builder(this)
                .setTitle(getErrorTitle())
                .setMessage(mPresenter.getMessageByException(error, this))
                .setPositiveButton(android.R.string.ok, { dialog, which -> dialog.dismiss() })
                .show()
    }

    fun setUpdateMenuItemDelegat(delegat: (() -> Unit)?) {
        mUpdateMenuItemDelegat = delegat
    }

    private fun initToolbar() {
        setSupportActionBar(ac_main_toolbar)

        val drawerToggle = object : ActionBarDrawerToggle(this,
                ac_main_drawer,
                ac_main_toolbar,
                0,
                0) {

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, 0F)
            }
        }

        drawerToggle.syncState()

        ac_main_toolbar.setNavigationOnClickListener({
            openCloseSideMenu()
        })
    }

    private fun createComponent() {
        val component = DaggerFragmentScreenComponent.builder()
                .rootComponent(getRootComponent())
                .fragmentScreenModule(FragmentScreenModule(this))
                .build()

        component.inject(mPresenter)
    }

    private fun getErrorTitle(): SpannableStringBuilder {
        val foregroundColorSpan = ForegroundColorSpan(ContextCompat.getColor(this, R.color.redColor))

        val title = getString(R.string.error_title)
        val titleBuilder = SpannableStringBuilder(title)

        titleBuilder.setSpan(
                foregroundColorSpan,
                0,
                title.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return titleBuilder
    }

    private fun openCloseSideMenu() {
        if (ac_main_drawer.isDrawerOpen(Gravity.END)) {
            ac_main_drawer.closeDrawer(Gravity.END)
        } else {
            ac_main_drawer.openDrawer(Gravity.END)
        }
    }

    override fun getActivity(): AppCompatActivity = this
    override fun getContainerId(): Int = R.id.ac_main_fl_fragment
    override fun getRootComponent(): RootComponent = (application as RusradioApp).getRootComponent()
}

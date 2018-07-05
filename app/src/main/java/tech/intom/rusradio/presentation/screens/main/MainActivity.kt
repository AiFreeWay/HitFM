package tech.intom.rusradio.presentation.screens.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import tech.intom.rusradio.presentation.screens.abstractions.MainView
import com.arellomobile.mvp.presenter.InjectPresenter
import tech.intom.rusradio.R
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.application.RusradioApp
import tech.intom.rusradio.application.di.components.RootComponent
import tech.intom.rusradio.application.di.modules.FragmentScreenModule
import tech.intom.rusradio.presentation.screens.abstractions.BaseActivity
import tech.intom.rusradio.presentation.screens.abstractions.FragmentsScreen
import android.text.Spanned
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import kotlinx.android.synthetic.main.ac_main.*
import tech.intom.rusradio.application.di.components.DaggerFragmentScreenComponent

/**
 * Created by root on 16.04.18.
 */
class MainActivity : BaseActivity(), MainView, FragmentsScreen {

    @InjectPresenter
    internal lateinit var mPresenter: MainPresenter

    private var mUpdateMenuItemDelegat: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RusradioApp).setCurrentActivity(this)
        Logger.logObjectCreating(this)
        setContentView(R.layout.ac_main_old)


        initToolbar()
        createComponent()

        ac_main_tv_menu_update.setOnClickListener({
            openCloseSideMenu()
            mUpdateMenuItemDelegat?.invoke()
        })
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
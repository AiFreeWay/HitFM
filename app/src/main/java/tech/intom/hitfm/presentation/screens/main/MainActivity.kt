package tech.intom.hitfm.presentation.screens.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.ac_main.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.App
import tech.intom.hitfm.application.di.components.DaggerFragmentScreenComponent
import tech.intom.hitfm.application.di.components.RootComponent
import tech.intom.hitfm.application.di.modules.FragmentScreenModule
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.screens.abstractions.BaseActivity
import tech.intom.hitfm.presentation.screens.abstractions.FragmentsScreen
import tech.intom.hitfm.presentation.screens.abstractions.MainView
import tech.intom.hitfm.presentation.utils.navigator.FragmentFactory
import com.squareup.picasso.Picasso
import tech.intom.hitfm.presentation.utils.CircleTransform
import java.lang.Exception
import tech.intom.hitfm.presentation.utils.RoundeBorderTransformer


class MainActivity : BaseActivity(), MainView, FragmentsScreen, BottomNavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    internal lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.logObjectCreating(this)
        setContentView(R.layout.ac_main)

        createComponent()
        ac_main_navigation.setOnNavigationItemSelectedListener(this)

        //start position
        mPresenter.putNavRecordToHistoryStack(
                R.id.navigation_radio,
                FragmentFactory.PROGRAMS_FRAGMENT_TAG)

        Picasso.get()
                .load("https://cdn.img.inosmi.ru/images/24126/31/241263151.jpg")
                .error(R.drawable.splash_image)
                .placeholder(R.drawable.splash_image)
                .transform(CircleTransform())
                .into(ac_main_img, object: Callback{
                    override fun onSuccess() {
                        RoundeBorderTransformer.transform(ac_main_img, resources)
                    }

                    override fun onError(e: Exception?) { }
                })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_radio -> {
                mPresenter.openNavFragment(item.itemId, FragmentFactory.PROGRAMS_FRAGMENT_TAG)
                return true
            }
            R.id.navigation_programs -> {
                mPresenter.openNavFragment(item.itemId, FragmentFactory.PROGRAMS_FRAGMENT_TAG)
                return true
            }
            R.id.navigation_news -> {
                mPresenter.openNavFragment(item.itemId, FragmentFactory.NEWS_FRAGMENT_TAG)
                return true
            }
            R.id.navigation_more -> {
                mPresenter.openNavFragment(item.itemId, FragmentFactory.NEWS_FRAGMENT_TAG)
                return true
            }
            else -> return false
        }
    }

    override fun selectBottomNavigationTab(tabId: Int) {
        ac_main_navigation.selectedItemId = tabId
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
                .setPositiveButton(android.R.string.ok, { dialog, _ -> dialog.dismiss() })
                .show()
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

    override fun getActivity(): AppCompatActivity = this
    override fun getContainerId(): Int = R.id.ac_main_fl_fragment
    override fun getRootComponent(): RootComponent = (application as App).getRootComponent()
}

package tech.intom.hitfm.presentation.screens.radio

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.LinkagePager
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fmt_radio.*
import me.crosswall.lib.coverflow.CoverFlow
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.abstractions.RadioView
import tech.intom.hitfm.presentation.screens.main.MainActivity
import me.crosswall.lib.coverflow.core.LinkagePagerContainer
import tech.intom.hitfm.application.di.components.DaggerRadioComponent
import tech.intom.hitfm.application.di.modules.RadioModule
import tech.intom.hitfm.domain.models.RadioItem
import tech.intom.hitfm.presentation.adapters.CorouselAdapter

class RadioFragment : MvpAppCompatFragment(), RadioView, FragmentChild<MainActivity> {

    private var customPagerContainer: LinkagePagerContainer? = null
    private var pager: LinkagePager? = null
    private var appBarLayout: AppBarLayout? = null
    private var parallaxHeight: Int = 0
    private var tab: View? = null

    internal inner class MyListPagerAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return 5
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val view = DataDemoView(context)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }


        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }

    // 333333333333333333333333333333333333333333333333333333333333333333333333333333333333

    @InjectPresenter
    internal lateinit var mPresenter: RadioPresenter

    /*private var mOnEmptyData = { isEmpty: Boolean ->
        if (isEmpty) {
            fmt_radio_tv_no_data.visibility = View.VISIBLE
        } else {
            fmt_radio_tv_no_data.visibility = View.GONE
        }
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)

        return inflater.inflate(R.layout.fmt_radio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        createComponent()
        initViewPager()
    }

    override fun loadModel(model: Model<List<RadioItem>>) {
        getParentView().setProgressState(model.isLoading)

        if (model.isSuccess) {

        } else if (model.isError) {
            getParentView().showErrorDialog(model.error!!)
        }
    }

    private fun createComponent() {
        val component  = DaggerRadioComponent.builder()
                .rootComponent(getParentView().getRootComponent())
                .radioModule(RadioModule())
                .build()

        component.inject(mPresenter)
    }

    private fun initViewPager() {
        parallaxHeight = resources.getDimensionPixelSize(R.dimen.cover_pager_height) - resources.getDimensionPixelSize(R.dimen.tab_height)

        Log.d("###", "parallaxHeight:$parallaxHeight")

        appBarLayout = radio_appbar

        appBarLayout!!.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            // Log.d("###","verticalOffset: " + Math.abs(verticalOffset));
            if (Math.abs(verticalOffset) >= parallaxHeight) {
                tab!!.visibility = View.VISIBLE
            } else {
                tab!!.visibility = View.GONE
            }
        })

        customPagerContainer = radio_pager_container

        tab = radio_tab

        val cover = customPagerContainer!!.viewPager

        val coverAdapter = CorouselAdapter(context!!)
        cover.adapter = coverAdapter
        cover.offscreenPageLimit = 4

        CoverFlow.Builder()
                .withLinkage(cover)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .build()

        pager = radio_pager

        val adapter = MyListPagerAdapter()

        pager!!.offscreenPageLimit = 4
        pager!!.adapter = adapter

        cover.setLinkagePager(pager)
        pager!!.setLinkagePager(cover)
    }

    override fun getParentView() = activity as MainActivity
}
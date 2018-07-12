package tech.intom.hitfm.presentation.screens.radio

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.LinkagePager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fmt_radio.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.abstractions.RadioView
import tech.intom.hitfm.presentation.screens.main.MainActivity
import tech.intom.hitfm.presentation.screens.radio.RadioPresenter
import android.view.Gravity
import android.widget.TextView
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.MotionEvent
import me.crosswall.lib.coverflow.core.CoverTransformer
import me.crosswall.lib.coverflow.core.PagerContainer
import tech.intom.hitfm.application.di.components.DaggerRadioComponent
import tech.intom.hitfm.application.di.modules.RadioModule
import tech.intom.hitfm.domain.models.RadioItem

class RadioFragment : MvpAppCompatFragment(), RadioView, FragmentChild<MainActivity> {

    @InjectPresenter
    internal lateinit var mPresenter: RadioPresenter

    /*private lateinit var mAdapter: MultyRvAdapter<ProgramItem>

    private var mOnEmptyData = { isEmpty: Boolean ->
        if (isEmpty) {
            //fmt_programs_tv_no_data.visibility = View.VISIBLE
        } else {
            //fmt_currency_tv_no_data.visibility = View.GONE
        }
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)

        return inflater.inflate(R.layout.fmt_radio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        createComponent()
        initPager()
    }

    private fun initPager() {
        val pager = fmt_radio_pager_container.viewPager

        val adapter = MyPagerAdapter()
        pager.adapter = adapter

        pager.offscreenPageLimit = adapter.count

        fmt_radio_pager.adapter = adapter
        fmt_radio_pager.offscreenPageLimit = adapter.count
        pager.clipChildren = false

        pager.setPageTransformer(false, CoverTransformer(0.3f, 0f, 0f, 0f))

        fmt_radio_pager.setOnTouchListener { view, motionEvent ->
            pager.onTouchEvent(motionEvent)
            false
        }

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            private var index = 0

            override fun onPageSelected(position: Int) {
                index = position

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val width = fmt_radio_pager.width
                fmt_radio_pager.scrollTo((width * position + width * positionOffset).toInt(), 0)
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    fmt_radio_pager.currentItem = index
                }

            }
        })
    }

    override fun loadModel(model: Model<List<RadioItem>>) {
        /*getParentView().setProgressState(model.isLoading)

        if (model.isSuccess) {
            mAdapter.loadData(model.data!!)
        } else if (model.isError) {
            getParentView().showErrorDialog(model.error!!)
        }*/
    }

    private fun createComponent() {
        val component  = DaggerRadioComponent.builder()
                .rootComponent(getParentView().getRootComponent())
                .radioModule(RadioModule())
                .build()

        component.inject(mPresenter)
    }

    private inner class MyPagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = TextView(activity)
            view.text = "Item $position"
            view.gravity = Gravity.CENTER
            view.setBackgroundColor(Color.argb(255, (position+1) * 50, (position+1) * 10, (position+1) * 50))

            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return 15
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }

    override fun getParentView() = activity as MainActivity
}
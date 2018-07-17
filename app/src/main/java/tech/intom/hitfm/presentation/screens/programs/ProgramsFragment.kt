package tech.intom.hitfm.presentation.screens.programs

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fmt_carousel.*
import me.crosswall.lib.coverflow.CoverFlow
import tech.intom.hitfm.R
import tech.intom.hitfm.application.di.components.DaggerProgramsComponent
import tech.intom.hitfm.application.di.modules.ProgramsModule
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.presentation.adapters.CorouselFragmentAdapter
import tech.intom.hitfm.presentation.adapters.CorouselImagesAdapter
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.abstractions.ProgramsView
import tech.intom.hitfm.presentation.screens.main.MainActivity

/**
 * Created by root on 16.04.18.
 */
class ProgramsFragment : MvpAppCompatFragment(), ProgramsView, FragmentChild<MainActivity>,
        AppBarLayout.OnOffsetChangedListener {

    private var mParallaxHeight: Int = 0

    @InjectPresenter
    internal lateinit var mPresenter: ProgramsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)

        return inflater.inflate(R.layout.fmt_carousel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        createComponent()
        initViewCarousel()
    }

    override fun loadModel(model: Model<List<ProgramItem>>) {
        getParentView().setProgressState(model.isLoading)

        if (model.isSuccess) {

        } else if (model.isError) {
            getParentView().showErrorDialog(model.error!!)
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        /*if (Math.abs(verticalOffset) >= mParallaxHeight) {
            fmt_carousel_top_tab!!.visibility = View.VISIBLE
        } else {
            fmt_carousel_top_tab!!.visibility = View.GONE
        }*/
    }

    private fun createComponent() {
        val component  = DaggerProgramsComponent.builder()
                .rootComponent(getParentView().getRootComponent())
                .programsModule(ProgramsModule())
                .build()

        component.inject(mPresenter)
    }

    private fun initViewCarousel() {
        mParallaxHeight = resources.getDimensionPixelSize(R.dimen.cover_pager_height) - resources.getDimensionPixelSize(R.dimen.radio_tab_height)

        fmt_carousel_appbar!!.addOnOffsetChangedListener(this)

        val imagesViewPager = fmt_carousel_image_pager_container!!.viewPager

        imagesViewPager.adapter = CorouselImagesAdapter(context!!)
        imagesViewPager.offscreenPageLimit = 4

        CoverFlow.Builder()
                .withLinkage(imagesViewPager)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .build()

        fmt_carousel_fragment_pager!!.offscreenPageLimit = 4
        fmt_carousel_fragment_pager!!.adapter = CorouselFragmentAdapter(childFragmentManager)

        imagesViewPager.setLinkagePager(fmt_carousel_fragment_pager)
        fmt_carousel_fragment_pager!!.setLinkagePager(imagesViewPager)
    }

    override fun getParentView() = activity as MainActivity
}
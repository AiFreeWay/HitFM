package tech.intom.hitfm.presentation.screens.radio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fmt_carousel.*
import me.crosswall.lib.coverflow.CoverFlow
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.abstractions.RadioView
import tech.intom.hitfm.presentation.screens.main.MainActivity
import tech.intom.hitfm.application.di.components.DaggerRadioComponent
import tech.intom.hitfm.application.di.modules.RadioModule
import tech.intom.hitfm.domain.models.RadioItem
import tech.intom.hitfm.presentation.adapters.CorouselImagesAdapter
import tech.intom.hitfm.presentation.adapters.CorouselFragmentAdapter
import tech.intom.hitfm.presentation.models.CarouselModel
import tech.intom.hitfm.presentation.utils.CorousePageChangeListener

class RadioFragment : MvpAppCompatFragment(), RadioView, FragmentChild<MainActivity> {

    private var mParallaxHeight: Int = 0

    @InjectPresenter
    internal lateinit var mPresenter: RadioPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)

        return inflater.inflate(R.layout.fmt_carousel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        createComponent()
        initViewCarousel()
    }

    override fun onResume() {
        super.onResume()
        getParentView().showBackNavigateToolbar(false)
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

    private fun initViewCarousel() {
        mParallaxHeight = resources.getDimensionPixelSize(R.dimen.cover_pager_height)

        val imagesViewPager = fmt_carousel_image_pager_container!!.viewPager

        imagesViewPager.addOnPageChangeListener(CorousePageChangeListener(v_carousel_top_title, v_carousel_top_circle_btn))

        imagesViewPager.adapter = CorouselImagesAdapter(context!!)
        imagesViewPager.offscreenPageLimit = 4

        CoverFlow.Builder()
                .withLinkage(imagesViewPager)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .build()

        fmt_carousel_fragment_pager!!.offscreenPageLimit = 4

        val list = ArrayList<CarouselModel>()

        val model1 = CarouselModel()
        model1.setIsHaveRadioButton(true)
        model1.setIsHaveText(true)

        list.add(model1)
        list.add(model1)
        list.add(model1)
        list.add(model1)

        fmt_carousel_fragment_pager!!.adapter = CorouselFragmentAdapter(childFragmentManager, list)

        imagesViewPager.setLinkagePager(fmt_carousel_fragment_pager)
        fmt_carousel_fragment_pager!!.setLinkagePager(imagesViewPager)
    }

    override fun getParentView() = activity as MainActivity
}
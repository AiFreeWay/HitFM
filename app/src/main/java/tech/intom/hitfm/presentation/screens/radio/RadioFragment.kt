package tech.intom.hitfm.presentation.screens.radio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fmt_radio.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.abstractions.RadioView
import tech.intom.hitfm.presentation.screens.main.MainActivity
import me.crosswall.lib.coverflow.core.CoverTransformer
import tech.intom.hitfm.application.di.components.DaggerRadioComponent
import tech.intom.hitfm.application.di.modules.RadioModule
import tech.intom.hitfm.domain.models.RadioItem
import tech.intom.hitfm.presentation.adapters.CorouselAdapter
import tech.intom.hitfm.presentation.adapters.CorouselFragmentAdapter
import tech.intom.hitfm.presentation.utils.CorousePageChangeListener

class RadioFragment : MvpAppCompatFragment(), RadioView, FragmentChild<MainActivity> {

    @InjectPresenter
    internal lateinit var mPresenter: RadioPresenter

    private var mOnEmptyData = { isEmpty: Boolean ->
        if (isEmpty) {
            fmt_radio_tv_no_data.visibility = View.VISIBLE
        } else {
            fmt_radio_tv_no_data.visibility = View.GONE
        }
    }

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
        val imagePager = fmt_radio_image_pager.viewPager

        val fragmentAdapter = CorouselFragmentAdapter(childFragmentManager) // ForFragment
        imagePager.adapter = CorouselAdapter(context!!) //For view

        imagePager.offscreenPageLimit = fragmentAdapter.count

        fmt_radio_fragment_pager.adapter = fragmentAdapter
        fmt_radio_fragment_pager.offscreenPageLimit = fragmentAdapter.count

        imagePager.clipChildren = false

        imagePager.setPageTransformer(false, CoverTransformer(0.3f, 0f, 0f, 0f))

        fmt_radio_fragment_pager.setOnTouchListener { _, motionEvent ->
            imagePager.onTouchEvent(motionEvent)
            false
        }

        imagePager.addOnPageChangeListener(CorousePageChangeListener(imagePager))
    }

    override fun getParentView() = activity as MainActivity
}
package tech.intom.hitfm.presentation.screens.carousel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fmt_carousel_body.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.AudioContainerItem
import tech.intom.hitfm.domain.models.AudioItem
import tech.intom.hitfm.presentation.adapters.MultyRvAdapter
import tech.intom.hitfm.presentation.adapters.holders.AudioContainerHolder
import tech.intom.hitfm.presentation.adapters.holders.AudioHolder
import tech.intom.hitfm.presentation.models.CarouselModel
import java.util.ArrayList

class CarouselBodyFragment : Fragment() {

    private lateinit var mCarouselModel: CarouselModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_carousel_body, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (mCarouselModel.isHaveText()) {
            fmt_carousel_body_text.visibility = View.VISIBLE
            fmt_carousel_body_text.setText("Prepare to supply your Liquid Funk presentation through 3 GB of high-end Drum & Bass and Drumstep samples. Superfluidity contains over 2,000 samples and loops ready to bring grooves into your projects. Inside you expect over 800 flawlessly created samples of mighty barrels, brisk snails.")
            fmt_carousel_body_shewron.visibility = View.VISIBLE

            fmt_carousel_body_shewron.setOnClickListener({
                fmt_carousel_body_text.maxLines = Int.MAX_VALUE
                fmt_carousel_body_shewron.visibility = GONE
                fmt_carousel_body_shewron.setOnClickListener(null)
            })
        } else {
            fmt_carousel_body_text.visibility = View.GONE
            fmt_carousel_body_shewron.visibility = GONE
        }

        if (mCarouselModel.isHaveButton()) {
            fmt_carousel_body_button.visibility = View.VISIBLE
        } else {
            fmt_carousel_body_button.visibility = View.GONE
        }

        if (mCarouselModel.isHaveRadioButton()) {
            fmt_carousel_body_radiobutton.visibility = View.VISIBLE
        } else {
            fmt_carousel_body_radiobutton.visibility = View.GONE
        }

        if (mCarouselModel.isHaveListView()) {
            fmt_carousel_body_list.visibility = View.VISIBLE

            val adapter = MultyRvAdapter(AudioHolder(context!!), null)
            fmt_carousel_body_list.layoutManager = LinearLayoutManager(context)
            fmt_carousel_body_list.adapter = adapter

            fmt_carousel_body_list.isNestedScrollingEnabled = false

            val mockData = ArrayList<AudioItem>()
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())
            mockData.add(AudioItem())

            adapter.loadData(mockData)
        } else {
            fmt_carousel_body_list.visibility = View.GONE
        }

        if (mCarouselModel.isHaveGridView()) {
            fmt_carousel_body_gridlist.visibility = View.VISIBLE

            val adapter = MultyRvAdapter(AudioContainerHolder(context!!), null)
            fmt_carousel_body_gridlist.layoutManager = GridLayoutManager(context, 2)
            fmt_carousel_body_gridlist.adapter = adapter

            val mockData = ArrayList<AudioContainerItem>()
            mockData.add(AudioContainerItem())
            mockData.add(AudioContainerItem())
            mockData.add(AudioContainerItem())
            mockData.add(AudioContainerItem())

            adapter.loadData(mockData)
        } else {
            fmt_carousel_body_gridlist.visibility = View.GONE
        }
    }

    fun setCatouselModel(model: CarouselModel) {
        mCarouselModel = model
    }
}
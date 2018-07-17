package tech.intom.hitfm.presentation.screens.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fmt_carousel_body.*
import tech.intom.hitfm.R
import tech.intom.hitfm.R.id.*
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.ProgramInfoItem
import tech.intom.hitfm.presentation.adapters.MultyRvAdapter
import tech.intom.hitfm.presentation.adapters.holders.GridHodler
import java.util.ArrayList

class CarouselBodyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_carousel_body, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fmt_carousel_body_text.visibility = View.GONE
        fmt_carousel_body_button.visibility = View.GONE

        val adapter = MultyRvAdapter(GridHodler(context!!), null)

        fmt_carousel_body_list.layoutManager = GridLayoutManager(context, 2)
        fmt_carousel_body_list.adapter = adapter

        val mockData = ArrayList<ProgramInfoItem>()
        mockData.add(ProgramInfoItem())
        mockData.add(ProgramInfoItem())
        mockData.add(ProgramInfoItem())
        mockData.add(ProgramInfoItem())

        adapter.loadData(mockData)
    }
}
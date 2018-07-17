package tech.intom.hitfm.presentation.screens.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fmt_carousel_body.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.AudioContainerItem
import tech.intom.hitfm.domain.models.AudioItem
import tech.intom.hitfm.presentation.adapters.MultyRvAdapter
import tech.intom.hitfm.presentation.adapters.holders.AudioContainerHolder
import tech.intom.hitfm.presentation.adapters.holders.AudioHolder
import java.util.ArrayList

class CarouselBodyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_carousel_body, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fmt_carousel_body_text.visibility = View.VISIBLE

        fmt_carousel_body_text.setText("AWD awdawdaw awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd awdawdaw awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd awdawdaw awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd awdawdaw awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd awdawdaw awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd awdawdaw awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd awdawdaw awdawd awda dawd awd aw awdaw aw awdawdwdw wdw w wd wd wdw wd wd")

        fmt_carousel_body_shewron.setOnClickListener({
            fmt_carousel_body_text.maxLines = Int.MAX_VALUE
            fmt_carousel_body_shewron.visibility = GONE
            fmt_carousel_body_shewron.setOnClickListener(null)
        })

        fmt_carousel_body_button.visibility = View.GONE

        //val adapter = MultyRvAdapter(AudioContainerHolder(context!!), null)
        val adapter = MultyRvAdapter(AudioHolder(context!!), null)

        //fmt_carousel_body_list.layoutManager = GridLayoutManager(context, 2)
        fmt_carousel_body_list.layoutManager = LinearLayoutManager(context)
        fmt_carousel_body_list.adapter = adapter

        /*val mockData = ArrayList<AudioContainerItem>()
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())*/

        val mockData = ArrayList<AudioItem>()
        mockData.add(AudioItem())
        mockData.add(AudioItem())
        mockData.add(AudioItem())
        mockData.add(AudioItem())

        adapter.loadData(mockData)
    }
}
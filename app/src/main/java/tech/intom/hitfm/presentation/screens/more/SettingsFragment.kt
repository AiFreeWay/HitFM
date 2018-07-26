package tech.intom.hitfm.presentation.screens.more

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fmt_settings.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.adapters.MultyRvAdapter
import tech.intom.hitfm.presentation.adapters.holders.FrequenceHolder
import tech.intom.hitfm.presentation.models.Frequence
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.main.MainActivity
import java.util.ArrayList

class SettingsFragment : MvpAppCompatFragment(), FragmentChild<MainActivity> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MultyRvAdapter(FrequenceHolder(context!!), null)

        fmt_settings_list.layoutManager = LinearLayoutManager(context)
        fmt_settings_list.adapter = adapter

        val mockData = ArrayList<Frequence>()
        mockData.add(Frequence("Абакан", 102.9F, false))
        mockData.add(Frequence("Алексеевка", 102.9F, false))
        mockData.add(Frequence("Анжеро-Судженск", 102.9F, false))
        mockData.add(Frequence("Балезино", 102.9F, true))
        mockData.add(Frequence("Белебей", 102.9F, false))
        mockData.add(Frequence("Березники", 102.9F, false))

        adapter.loadData(mockData)
    }

    override fun onResume() {
        super.onResume()
        getParentView().showBackNavigateToolbar(true)
    }

    override fun getParentView() = activity as MainActivity
}
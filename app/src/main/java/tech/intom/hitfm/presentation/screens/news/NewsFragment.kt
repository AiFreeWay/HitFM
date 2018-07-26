package tech.intom.hitfm.presentation.screens.news

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fmt_list.*
import kotlinx.android.synthetic.main.fmt_news.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.AudioContainerItem
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.presentation.adapters.MultyRvAdapter
import tech.intom.hitfm.presentation.adapters.holders.NewsHolder
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.ProgramsView
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.main.MainActivity
import tech.intom.hitfm.presentation.utils.navigator.FragmentFactory
import java.util.ArrayList

/**
 * Created by root on 16.04.18.
 */
class NewsFragment : MvpAppCompatFragment(), ProgramsView, FragmentChild<MainActivity> {

    //@InjectPresenter
    //internal lateinit var mPresenter: NewsPresenter

    private lateinit var mAdapter: MultyRvAdapter<AudioContainerItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createComponent()

        mAdapter = MultyRvAdapter(NewsHolder(context!!, {
            getParentView().getPresenter().openFragment(FragmentFactory.NEWS_ITEM_FRAGMENT_TAG)
        }), null)

        fmt_news_list.layoutManager = GridLayoutManager(context, 2)
        fmt_news_list.adapter = mAdapter

        val mockData = ArrayList<AudioContainerItem>()
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())
        mockData.add(AudioContainerItem())

        mAdapter.loadData(mockData)
    }

    override fun onResume() {
        super.onResume()
        getParentView().showBackNavigateToolbar(false)
    }

    override fun loadModel(model: Model<List<ProgramItem>>) {
        getParentView().setProgressState(model.isLoading)

        if (model.isSuccess) {
            //mAdapter.loadData(model.data!!)
        } else if (model.isError) {
            getParentView().showErrorDialog(model.error!!)
        }
    }

    private fun createComponent() {
        //val component  = DaggerProgramsComponent.builder()
        //        .rootComponent(getParentView().getRootComponent())
        //        .currencyModule(ProgramsModule())
        //        .build()

        //component.inject(mPresenter)
    }

    override fun getParentView() = activity as MainActivity
}
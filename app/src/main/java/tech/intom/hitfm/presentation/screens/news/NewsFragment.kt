package tech.intom.hitfm.presentation.screens.news

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fmt_news.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.application.di.components.DaggerProgramsComponent
import tech.intom.hitfm.application.di.modules.ProgramsModule
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.presentation.adapters.MultyRvAdapter
import tech.intom.hitfm.presentation.adapters.holders.CurrencyHolder
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.ProgramsView
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.main.MainActivity
import tech.intom.hitfm.presentation.screens.programs.ProgramsPresenter

/**
 * Created by root on 16.04.18.
 */
class NewsFragment : MvpAppCompatFragment(), ProgramsView, FragmentChild<MainActivity> {

    //@InjectPresenter
    //internal lateinit var mPresenter: NewsPresenter

    private lateinit var mAdapter: MultyRvAdapter<ProgramItem>

    private var mOnEmptyData = { isEmpty: Boolean ->
        if (isEmpty) {
            fmt_news_tv_no_data.visibility = View.VISIBLE
        } else {
            fmt_news_tv_no_data.visibility = View.GONE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createComponent()

        mAdapter = MultyRvAdapter(CurrencyHolder(context!!), mOnEmptyData)

        fmt_news_rv_list.layoutManager = LinearLayoutManager(context)
        fmt_news_rv_list.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()
        getParentView().setUpdateMenuItemDelegat {
            //mPresenter.getNews()
        }
    }

    override fun onStop() {
        super.onStop()
        getParentView().setUpdateMenuItemDelegat(null)
    }

    override fun loadModel(model: Model<List<ProgramItem>>) {
        getParentView().setProgressState(model.isLoading)

        if (model.isSuccess) {
            mAdapter.loadData(model.data!!)
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
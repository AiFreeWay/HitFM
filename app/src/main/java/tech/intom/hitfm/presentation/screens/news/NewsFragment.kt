package tech.intom.hitfm.presentation.screens.news

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fmt_list.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.presentation.adapters.MultyRvAdapter
import tech.intom.hitfm.presentation.adapters.holders.NewsHolder
import tech.intom.hitfm.presentation.models.Model
import tech.intom.hitfm.presentation.screens.abstractions.ProgramsView
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.main.MainActivity

/**
 * Created by root on 16.04.18.
 */
class NewsFragment : MvpAppCompatFragment(), ProgramsView, FragmentChild<MainActivity> {

    //@InjectPresenter
    //internal lateinit var mPresenter: NewsPresenter

    private lateinit var mAdapter: MultyRvAdapter<ProgramItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createComponent()

        mAdapter = MultyRvAdapter(NewsHolder(context!!), null)

        fmt_list_rv_list.layoutManager = LinearLayoutManager(context)
        fmt_list_rv_list.adapter = mAdapter
    }

    /*override fun onStart() {
        super.onStart()
        getParentView().setUpdateMenuItemDelegat {
            //mPresenter.getNews()
        }
    }

    override fun onStop() {
        super.onStop()
        getParentView().setUpdateMenuItemDelegat(null)
    }*/

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
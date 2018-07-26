package tech.intom.hitfm.presentation.screens.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fmt_more.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.main.MainActivity
import tech.intom.hitfm.presentation.utils.navigator.FragmentFactory

class MoreFragment : MvpAppCompatFragment(), FragmentChild<MainActivity> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_more, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fmt_more_contacts.setOnClickListener({
            getParentView().getPresenter().openFragment(FragmentFactory.CONTACTS_FRAGMENT_TAG)
        })

        fmt_more_frequence.setOnClickListener({
            getParentView().getPresenter().openFragment(FragmentFactory.SETTINGS_FRAGMENT_TAG)
        })
    }

    override fun onResume() {
        super.onResume()
        getParentView().showBackNavigateToolbar(false)
    }

    override fun getParentView() = activity as MainActivity
}
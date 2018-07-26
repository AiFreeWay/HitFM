package tech.intom.hitfm.presentation.screens.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.main.MainActivity

class ContactsFragment : MvpAppCompatFragment(), FragmentChild<MainActivity> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_contacts, container, false)
    }

    override fun onResume() {
        super.onResume()
        getParentView().showBackNavigateToolbar(true)
    }

    override fun getParentView() = activity as MainActivity
}
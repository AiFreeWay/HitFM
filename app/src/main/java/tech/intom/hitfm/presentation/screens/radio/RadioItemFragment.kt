package tech.intom.hitfm.presentation.screens.radio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger

class RadioItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_radio_item, container, false)
    }
}
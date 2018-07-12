package tech.intom.hitfm.presentation.screens.abstractions

import com.arellomobile.mvp.MvpView
import tech.intom.hitfm.domain.models.RadioItem
import tech.intom.hitfm.presentation.models.Model

interface RadioView : MvpView {

    fun loadModel(model: Model<List<RadioItem>>)
}
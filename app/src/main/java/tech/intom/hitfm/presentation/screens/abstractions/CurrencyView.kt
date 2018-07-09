package tech.intom.hitfm.presentation.screens.abstractions

import com.arellomobile.mvp.MvpView
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.presentation.models.Model

/**
 * Created by root on 16.04.18.
 */
interface CurrencyView : MvpView {

    fun loadModel(model: Model<List<ProgramItem>>)
}
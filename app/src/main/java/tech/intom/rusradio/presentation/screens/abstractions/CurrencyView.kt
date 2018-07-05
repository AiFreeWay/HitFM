package tech.intom.rusradio.presentation.screens.abstractions

import com.arellomobile.mvp.MvpView
import tech.intom.rusradio.application.models.News
import tech.intom.rusradio.presentation.models.Model

/**
 * Created by root on 16.04.18.
 */
interface CurrencyView : MvpView {

    fun loadModel(model: Model<List<News>>)
}
package tech.intom.hitfm.presentation.screens.radio

import com.arellomobile.mvp.InjectViewState
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.executors.RadioExecutor
import tech.intom.hitfm.domain.models.RadioItem
import tech.intom.hitfm.presentation.screens.abstractions.BasePresenter
import tech.intom.hitfm.presentation.screens.abstractions.RadioView
import tech.intom.hitfm.presentation.utils.ModelFactory
import javax.inject.Inject

@InjectViewState
class RadioPresenter : BasePresenter<RadioView>() {

    @Inject
    lateinit var mRadioExecutor: RadioExecutor

    init {
        Logger.logObjectCreating(this)
    }

    override fun attachView(view: RadioView?) {
        super.attachView(view)
        getRadio()
    }

    fun getRadio() {
        mDisposables.add(mRadioExecutor.getRadio()
                .doOnSubscribe({ onLoading() })
                .subscribe(this::onLoaded, this::onException))
    }

    private fun onLoading() {
        viewState.loadModel(ModelFactory.createLoadingModel())
    }

    private fun onLoaded(payments: List<RadioItem>) {
        viewState.loadModel(ModelFactory.createLoadedDataModel(payments))
    }

    private fun onException(exception: Throwable) {
        handleException(exception)

        viewState.loadModel(ModelFactory.createErrorModel(exception))
    }
}
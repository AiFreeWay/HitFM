package tech.intom.hitfm.presentation.screens.programs

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.domain.executors.ProgramsExecutor
import tech.intom.hitfm.presentation.screens.abstractions.BasePresenter
import tech.intom.hitfm.presentation.screens.abstractions.ProgramsView
import tech.intom.hitfm.presentation.utils.ModelFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by root on 16.04.18.
 */
@InjectViewState
class ProgramsPresenter : BasePresenter<ProgramsView>() {

    @Inject lateinit var mProgramsExecutor: ProgramsExecutor

    init {
        Logger.logObjectCreating(this)
    }

    override fun attachView(view: ProgramsView?) {
        super.attachView(view)
        //initCurrencyLoadInterval()
    }

    fun getPrograms() {
        mDisposables.add(mProgramsExecutor.getPrograms()
                .doOnSubscribe({ onLoading() })
                .subscribe(this::onLoaded, this::onException))
    }

    /*private fun initCurrencyLoadInterval() {
        mDisposables.add(Observable.interval(0,15, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ getPrograms() }, this::handleException))
    }*/

    private fun onLoading() {
        viewState.loadModel(ModelFactory.createLoadingModel())
    }

    private fun onLoaded(payments: List<ProgramItem>) {
        viewState.loadModel(ModelFactory.createLoadedDataModel(payments))
    }

    private fun onException(exception: Throwable) {
        handleException(exception)

        viewState.loadModel(ModelFactory.createErrorModel(exception))
    }
}
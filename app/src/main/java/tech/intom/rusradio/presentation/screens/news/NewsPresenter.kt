package tech.intom.rusradio.presentation.screens.news

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.application.models.News
import tech.intom.rusradio.domain.executors.NewsExecutor
import tech.intom.rusradio.presentation.screens.abstractions.BasePresenter
import tech.intom.rusradio.presentation.screens.abstractions.CurrencyView
import tech.intom.rusradio.presentation.utils.ModelFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by root on 16.04.18.
 */
@InjectViewState
class NewsPresenter : BasePresenter<CurrencyView>() {

    @Inject lateinit var mNewsExecutor: NewsExecutor

    init {
        Logger.logObjectCreating(this)
    }

    override fun attachView(view: CurrencyView?) {
        super.attachView(view)
        initCurrencyLoadInterval()
    }

    fun getCurrency() {
        mDisposables.add(mNewsExecutor.getCurrency()
                .doOnSubscribe({ onLoading() })
                .subscribe(this::onLoaded, this::onException))
    }

    private fun initCurrencyLoadInterval() {
        mDisposables.add(Observable.interval(0,15, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ getCurrency() }, this::handleException))
    }

    private fun onLoading() {
        viewState.loadModel(ModelFactory.createLoadingModel())
    }

    private fun onLoaded(payments: List<News>) {
        viewState.loadModel(ModelFactory.createLoadedDataModel(payments))
    }

    private fun onException(exception: Throwable) {
        handleException(exception)

        viewState.loadModel(ModelFactory.createErrorModel(exception))
    }
}
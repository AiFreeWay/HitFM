package tech.intom.rusradio.domain.executors

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.application.models.News
import tech.intom.rusradio.data.repository.abstractions.CurrencyRepository
import tech.intom.rusradio.domain.mappers.CurrencyMapper

/**
 * Created by root on 16.04.18.
 */
class NewsExecutor constructor(private val mRepository: CurrencyRepository) {

    init {
        Logger.logObjectCreating(this)
    }

    fun getCurrency(): Observable<List<News>> {
        return mRepository.getCurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map({ response -> CurrencyMapper.mapCurrencyList(response) })
    }
}
package tech.intom.hitfm.domain.executors

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.data.repository.abstractions.RadioRepository
import tech.intom.hitfm.domain.mappers.RadioMapper
import tech.intom.hitfm.domain.models.RadioItem

class RadioExecutor constructor(private val mRepository: RadioRepository) {

    init {
        Logger.logObjectCreating(this)
    }

    fun getRadio(): Observable<List<RadioItem>> {
        return mRepository.getRadio()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map({ response -> RadioMapper.mapRadio(response) })
    }
}
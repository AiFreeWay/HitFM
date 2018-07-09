package tech.intom.hitfm.domain.executors

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.domain.models.ProgramItem
import tech.intom.hitfm.data.repository.abstractions.ProgramsRepository
import tech.intom.hitfm.domain.mappers.ProgramsMapper

/**
 * Created by root on 16.04.18.
 */
class ProgramsExecutor constructor(private val mRepository: ProgramsRepository) {

    init {
        Logger.logObjectCreating(this)
    }

    fun getPrograms(): Observable<List<ProgramItem>> {
        return mRepository.getPrograms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map({ response -> ProgramsMapper.mapPrograms(response) })
    }
}
package tech.intom.hitfm.data.repository.abstractions

import io.reactivex.Observable
import tech.intom.hitfm.data.network.responses.ProgramsResponse

/**
 * Created by root on 09.04.18.
 */
interface ProgramsRepository {

    fun getPrograms(): Observable<ProgramsResponse>
}
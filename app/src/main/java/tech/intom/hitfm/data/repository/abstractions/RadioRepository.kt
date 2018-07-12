package tech.intom.hitfm.data.repository.abstractions

import io.reactivex.Observable
import tech.intom.hitfm.data.network.responses.RadioResponse

interface RadioRepository {

    fun getRadio(): Observable<RadioResponse>
}
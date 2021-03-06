package tech.intom.hitfm.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import tech.intom.hitfm.data.network.responses.ProgramsResponse
import tech.intom.hitfm.data.network.responses.RadioResponse

/**
 * Created by root on 17.04.18.
 */
interface Api {

    @GET("/")
    fun getPrograms(): Observable<ProgramsResponse>

    @GET("/")
    fun getRadio(): Observable<RadioResponse>
}
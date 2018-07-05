package tech.intom.rusradio.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import tech.intom.rusradio.data.network.responses.CurrencyResponse

/**
 * Created by root on 17.04.18.
 */
interface Api {

    @GET("/stocks.json")
    fun getCurrency(): Observable<CurrencyResponse>
}
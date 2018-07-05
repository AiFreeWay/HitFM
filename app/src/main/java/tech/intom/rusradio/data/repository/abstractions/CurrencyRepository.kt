package tech.intom.rusradio.data.repository.abstractions

import io.reactivex.Observable
import tech.intom.rusradio.data.network.responses.CurrencyResponse

/**
 * Created by root on 09.04.18.
 */
interface CurrencyRepository {

    fun getCurrency(): Observable<CurrencyResponse>
}
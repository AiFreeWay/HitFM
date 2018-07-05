package tech.intom.rusradio.domain.mappers

import tech.intom.rusradio.application.models.News
import tech.intom.rusradio.data.models.CurrencyData
import tech.intom.rusradio.data.network.responses.CurrencyResponse

/**
 * Created by root on 17.04.18.
 */
object CurrencyMapper {

    fun mapCurrencyList(currencyResponse: CurrencyResponse): List<News> {
        val currency = ArrayList<News>()

        currencyResponse.stock.forEach {
            currency.add(mapCurrency(it))
        }

        return currency
    }

    fun mapCurrency(currencyData: CurrencyData): News {
        return News(currencyData.name,
                currencyData.volume.toString(),
                currencyData.price?.amount.toString())
    }
}
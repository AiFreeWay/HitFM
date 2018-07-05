package tech.intom.rusradio.data.models

/**
 * Created by root on 17.04.18.
 */
class CurrencyData {

    var name: String? = null
    var price: Price? = null
    var volume: Int? = null

    public class Price {

        var amount: Float? = null
    }
}
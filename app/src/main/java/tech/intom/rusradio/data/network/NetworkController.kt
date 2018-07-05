package tech.intom.rusradio.data.network

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.intom.rusradio.BuildConfig
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.data.network.responses.CurrencyResponse

/**
 * Created by root on 17.04.18.
 */
class NetworkController {

    private val DOMAIN = "phisix-api3.appspot.com"
    private val API_URL = "http://$DOMAIN/"
    private val mApiController: Api

    init {
        Logger.logObjectCreating(this)

        val gson = GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.Z")
                .create()

        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClientBuilder.build())
                .build()
        mApiController = retrofit.create(Api::class.java)
    }

    fun getPosts(): Observable<CurrencyResponse> = mApiController.getCurrency()
}
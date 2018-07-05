package tech.intom.rusradio.data.repository

import tech.intom.rusradio.application.exceptions.ExceptionStoreModel
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.data.repository.abstractions.CurrencyRepository
import tech.intom.rusradio.data.repository.abstractions.ExceptionRepository
import tech.intom.rusradio.data.network.NetworkController
import tech.intom.rusradio.data.preference.PreferenceStore
import javax.inject.Inject

/**
 * Created by root on 09.04.18.
 */
class Repository @Inject constructor(private val mNetworkController: NetworkController,
                                     private val mPreferenceStore: PreferenceStore) :
        ExceptionRepository, CurrencyRepository {

    init {
        Logger.logObjectCreating(this)
    }

    //Exceptions Repository

    override fun getExceptions() = mPreferenceStore.getExceptions()

    override fun putExceptions(exceptionsModel: ExceptionStoreModel) {
        mPreferenceStore.putException(exceptionsModel)
    }

    //CurrencyData Repository

    override fun getCurrency() = mNetworkController.getPosts()
}
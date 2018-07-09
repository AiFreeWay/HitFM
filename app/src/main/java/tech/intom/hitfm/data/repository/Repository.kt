package tech.intom.hitfm.data.repository

import tech.intom.hitfm.domain.models.ExceptionStoreModel
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.data.repository.abstractions.ProgramsRepository
import tech.intom.hitfm.data.repository.abstractions.ExceptionRepository
import tech.intom.hitfm.data.network.NetworkController
import tech.intom.hitfm.data.preference.PreferenceStore
import javax.inject.Inject

/**
 * Created by root on 09.04.18.
 */
class Repository @Inject constructor(private val mNetworkController: NetworkController,
                                     private val mPreferenceStore: PreferenceStore) :
        ExceptionRepository, ProgramsRepository {

    init {
        Logger.logObjectCreating(this)
    }

    //Exceptions Repository

    override fun getExceptions() = mPreferenceStore.getExceptions()

    override fun putExceptions(exceptionsModel: ExceptionStoreModel) {
        mPreferenceStore.putException(exceptionsModel)
    }

    //CurrencyData Repository

    override fun getPrograms() = mNetworkController.getPrograms()
}
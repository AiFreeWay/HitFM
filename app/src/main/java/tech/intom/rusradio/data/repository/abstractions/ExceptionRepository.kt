package tech.intom.rusradio.data.repository.abstractions

import tech.intom.rusradio.application.exceptions.ExceptionStoreModel

/**
 * Created by root on 10.04.18.
 */
interface ExceptionRepository {

    fun getExceptions(): Set<ExceptionStoreModel>
    fun putExceptions(exceptionsModel: ExceptionStoreModel)
}
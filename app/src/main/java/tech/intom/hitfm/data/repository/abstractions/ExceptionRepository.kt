package tech.intom.hitfm.data.repository.abstractions

import tech.intom.hitfm.domain.models.ExceptionStoreModel

/**
 * Created by root on 10.04.18.
 */
interface ExceptionRepository {

    fun getExceptions(): Set<ExceptionStoreModel>
    fun putExceptions(exceptionsModel: ExceptionStoreModel)
}
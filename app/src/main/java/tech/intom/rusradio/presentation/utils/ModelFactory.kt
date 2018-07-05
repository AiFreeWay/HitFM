package tech.intom.rusradio.presentation.utils

import tech.intom.rusradio.presentation.models.Model
import tech.intom.rusradio.presentation.models.SimpleModel

/**
 * Created by root on 09.04.18.
 */
object ModelFactory {

    fun <Type> createLoadedDataModel(data: Type?) = Model<Type>(data)

    fun <Type> createLoadingModel() = Model<Type>(true)

    fun <Type> createErrorModel(error: Throwable) = Model<Type>(error)


    fun createSimpleLoadedModel() = SimpleModel()

    fun createSimpleLoadModel(isLoading: Boolean) = SimpleModel(isLoading)

    fun createSimpleErrorModel(error: Throwable) = SimpleModel(error)
}
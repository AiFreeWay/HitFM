package tech.intom.rusradio.presentation.models

/**
 * Created by root on 10.04.18.
 */
open class SimpleModel {

    var isSuccess: Boolean
    var isLoading: Boolean
    var isError: Boolean
    var error: Throwable?

    constructor() {
        isSuccess = true
        isLoading = false
        isError = false
        error = null
    }

    constructor(isLoading: Boolean) {
        isSuccess = false
        this.isLoading = isLoading
        isError = false
        error = null
    }

    constructor(error: Throwable) {
        isSuccess = false
        isLoading = false
        isError = true
        this.error = error
    }
}
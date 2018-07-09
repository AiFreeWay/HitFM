package tech.intom.hitfm.presentation.models

/**
 * Created by root on 09.04.18.
 */
class Model<Type> : SimpleModel {

    var data: Type?

    constructor(data: Type?) : super() {
        this.data = data
    }

    constructor(isLoading: Boolean) : super(isLoading) {
        data = null
    }

    constructor(error: Throwable) : super (error) {
        data = null
    }
}
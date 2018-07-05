package tech.intom.rusradio.presentation.screens.abstractions

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import tech.intom.rusradio.application.exceptions.ExceptionHandler

/**
 * Created by root on 17.04.18.
 */
abstract class BasePresenter<V: MvpView> : MvpPresenter<V>()  {

    protected val mDisposables: CompositeDisposable = CompositeDisposable()

    override fun detachView(view: V?) {
        super.detachView(view)
        mDisposables.clear()
    }

    open fun handleException(exception: Throwable) {
        ExceptionHandler.handleException(exception)
    }
}
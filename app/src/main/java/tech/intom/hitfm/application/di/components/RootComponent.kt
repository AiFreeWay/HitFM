package tech.intom.hitfm.application.di.components

import android.content.Context
import dagger.Component
import tech.intom.hitfm.application.di.modules.RootModule
import tech.intom.hitfm.application.exceptions.ExceptionHandler
import tech.intom.hitfm.data.repository.abstractions.ExceptionRepository
import tech.intom.hitfm.data.network.NetworkController
import tech.intom.hitfm.data.preference.PreferenceStore

import javax.inject.Singleton

/**
 * Created by root on 09.04.18.
 */
@Singleton
@Component(modules = arrayOf(RootModule::class))
interface RootComponent {

    fun provideContext() : Context
    fun provideExceptionRepository() : ExceptionRepository
    fun providePreferenseStore() : PreferenceStore
    fun provideNetworkController() : NetworkController

    fun inject(exceptionHandler: ExceptionHandler)
}
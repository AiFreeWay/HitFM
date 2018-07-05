package tech.intom.rusradio.application.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.data.repository.Repository
import tech.intom.rusradio.data.repository.abstractions.*
import tech.intom.rusradio.data.network.NetworkController
import tech.intom.rusradio.data.preference.PreferenceStore
import tech.intom.rusradio.domain.executors.ExceptionExecutor
import javax.inject.Singleton

/**
 * Created by root on 09.04.18.
 */
@Module
class RootModule(private val mContext: Context) {

    init {
        Logger.logObjectCreating(this)
    }

    @Provides
    @Singleton
    fun  provideContext(): Context = mContext

    @Provides
    @Singleton
    fun providePreferenseStore(): PreferenceStore = PreferenceStore()

    @Provides
    @Singleton
    fun provideNetworkController(): NetworkController = NetworkController()

    @Provides
    @Singleton
    fun provideExceptionRepository(repository : Repository): ExceptionRepository = repository

    @Provides
    @Singleton
    fun provideExceptionExecutor(repository : ExceptionRepository, context: Context) = ExceptionExecutor(repository, context)
}
package tech.intom.hitfm.application.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.data.repository.Repository
import tech.intom.hitfm.data.repository.abstractions.*
import tech.intom.hitfm.data.network.NetworkController
import tech.intom.hitfm.data.preference.PreferenceStore
import tech.intom.hitfm.domain.executors.ExceptionExecutor
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
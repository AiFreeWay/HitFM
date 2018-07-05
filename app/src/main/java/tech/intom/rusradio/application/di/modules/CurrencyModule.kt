package tech.intom.rusradio.application.di.modules

import dagger.Module
import dagger.Provides
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.application.di.scopes.PerCurrency
import tech.intom.rusradio.data.repository.Repository
import tech.intom.rusradio.data.repository.abstractions.CurrencyRepository
import tech.intom.rusradio.domain.executors.NewsExecutor

/**
 * Created by root on 10.04.18.
 */
@Module
class CurrencyModule {

    init {
        Logger.logObjectCreating(this)
    }

    @Provides
    @PerCurrency
    fun provideCurrencyRepository(repository : Repository): CurrencyRepository = repository

    @Provides
    @PerCurrency
    fun provideCurrencyExecutor(repository: CurrencyRepository) = NewsExecutor(repository)
}
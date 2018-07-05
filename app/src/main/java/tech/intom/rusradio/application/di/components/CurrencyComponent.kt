package tech.intom.rusradio.application.di.components

import dagger.Component
import tech.intom.rusradio.application.di.modules.CurrencyModule
import tech.intom.rusradio.application.di.scopes.PerCurrency
import tech.intom.rusradio.data.repository.abstractions.CurrencyRepository
import tech.intom.rusradio.presentation.screens.news.NewsPresenter

/**
 * Created by root on 10.04.18.
 */
@PerCurrency
@Component(modules = arrayOf(CurrencyModule::class), dependencies = arrayOf(RootComponent::class))
interface CurrencyComponent {

    fun provideCurrencyRepository() : CurrencyRepository

    fun inject(presenter: NewsPresenter)
}
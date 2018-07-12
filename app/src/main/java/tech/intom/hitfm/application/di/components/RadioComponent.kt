package tech.intom.hitfm.application.di.components

import dagger.Component
import tech.intom.hitfm.application.di.modules.RadioModule
import tech.intom.hitfm.application.di.scopes.PerRadio
import tech.intom.hitfm.data.repository.abstractions.RadioRepository
import tech.intom.hitfm.presentation.screens.radio.RadioPresenter

@PerRadio
@Component(modules = arrayOf(RadioModule::class), dependencies = arrayOf(RootComponent::class))
interface RadioComponent {

    fun provideRadioRepository() : RadioRepository

    fun inject(presenter: RadioPresenter)
}
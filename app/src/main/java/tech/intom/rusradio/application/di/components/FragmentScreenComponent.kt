package tech.intom.rusradio.application.di.components

import dagger.Component
import tech.intom.rusradio.application.di.modules.FragmentScreenModule
import tech.intom.rusradio.application.di.scopes.PerFragmentsScreen
import tech.intom.rusradio.presentation.screens.main.MainPresenter

/**
 * Created by root on 09.04.18.
 */
@PerFragmentsScreen
@Component(modules = arrayOf(FragmentScreenModule::class), dependencies = arrayOf(RootComponent::class))
interface FragmentScreenComponent {

    fun inject(presenter: MainPresenter)
}
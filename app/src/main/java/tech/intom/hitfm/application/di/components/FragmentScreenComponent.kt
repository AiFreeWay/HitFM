package tech.intom.hitfm.application.di.components

import dagger.Component
import tech.intom.hitfm.application.di.modules.FragmentScreenModule
import tech.intom.hitfm.application.di.scopes.PerFragmentsScreen
import tech.intom.hitfm.presentation.screens.main.MainPresenter

/**
 * Created by root on 09.04.18.
 */
@PerFragmentsScreen
@Component(modules = arrayOf(FragmentScreenModule::class), dependencies = arrayOf(RootComponent::class))
interface FragmentScreenComponent {

    fun inject(presenter: MainPresenter)
}
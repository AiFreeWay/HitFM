package tech.intom.hitfm.application.di.components

import dagger.Component
import tech.intom.hitfm.application.di.modules.ProgramsModule
import tech.intom.hitfm.application.di.scopes.PerPrograms
import tech.intom.hitfm.data.repository.abstractions.ProgramsRepository
import tech.intom.hitfm.presentation.screens.programs.ProgramsPresenter

/**
 * Created by root on 10.04.18.
 */
@PerPrograms
@Component(modules = arrayOf(ProgramsModule::class), dependencies = arrayOf(RootComponent::class))
interface ProgramsComponent {

    fun provideProgramsRepository() : ProgramsRepository

    fun inject(presenter: ProgramsPresenter)
}
package tech.intom.hitfm.application.di.modules

import dagger.Module
import dagger.Provides
import tech.intom.hitfm.application.di.scopes.PerPrograms
import tech.intom.hitfm.application.di.scopes.PerRadio
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.data.repository.Repository
import tech.intom.hitfm.data.repository.abstractions.RadioRepository
import tech.intom.hitfm.domain.executors.RadioExecutor

@Module
class RadioModule {

    init {
        Logger.logObjectCreating(this)
    }

    @Provides
    @PerRadio
    fun provideRadioRepository(repository : Repository): RadioRepository = repository

    @Provides
    @PerRadio
    fun provideRadioExecutor(repository: RadioRepository) = RadioExecutor(repository)
}
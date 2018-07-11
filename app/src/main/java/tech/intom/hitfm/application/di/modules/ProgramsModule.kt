package tech.intom.hitfm.application.di.modules

import dagger.Module
import dagger.Provides
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.application.di.scopes.PerPrograms
import tech.intom.hitfm.data.repository.Repository
import tech.intom.hitfm.data.repository.abstractions.ProgramsRepository
import tech.intom.hitfm.domain.executors.ProgramsExecutor

/**
 * Created by root on 10.04.18.
 */
@Module
class ProgramsModule {

    init {
        Logger.logObjectCreating(this)
    }

    @Provides
    @PerPrograms
    fun provideProgramsRepository(repository : Repository): ProgramsRepository = repository

    @Provides
    @PerPrograms
    fun provideProgramsExecutor(repository: ProgramsRepository) = ProgramsExecutor(repository)
}
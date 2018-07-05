package tech.intom.rusradio.application.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import tech.intom.rusradio.application.Logger
import tech.intom.rusradio.application.di.scopes.PerFragmentsScreen
import tech.intom.rusradio.presentation.utils.navigator.FragmentNavigator
import tech.intom.rusradio.presentation.screens.abstractions.FragmentsScreen

/**
 * Created by root on 09.04.18.
 */
@Module
class FragmentScreenModule(fragmentScreen: FragmentsScreen) {

    private val mFragmentRouter: Router

    init {
        Logger.logObjectCreating(this)

        val cicerone = Cicerone.create()
        cicerone.navigatorHolder.setNavigator(FragmentNavigator(fragmentScreen))

        mFragmentRouter = cicerone.router
    }

    @Provides
    @PerFragmentsScreen
    fun provideFragmentRouter(): Router = mFragmentRouter
}
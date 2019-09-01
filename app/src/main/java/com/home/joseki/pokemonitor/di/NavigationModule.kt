package com.home.joseki.pokemonitor.di

import com.home.joseki.pokemonitor.di.view.navigation.MainRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import toothpick.config.Module

class NavigationModule: Module() {
    private val cicerone: Cicerone<MainRouter> = Cicerone.create(MainRouter())

    init {
        bind(MainRouter::class.java).toInstance(cicerone.router as MainRouter)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}
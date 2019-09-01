package com.home.joseki.pokemonitor.di

import com.home.joseki.pokemonitor.di.providers.PokemonInteractorProvider
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import toothpick.config.Module

class ProviderModule: Module() {
    init {
        bind(IPokemonInteractor::class.java).toProvider(PokemonInteractorProvider::class.java).providesSingletonInScope()
    }
}
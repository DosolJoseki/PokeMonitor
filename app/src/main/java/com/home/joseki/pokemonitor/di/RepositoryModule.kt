package com.home.joseki.pokemonitor.di

import com.home.joseki.pokemonitor.di.providers.PokemonRepositoryProvider
import com.home.joseki.pokemonitor.repositories.IPokemonRepositoriy
import toothpick.config.Module

class RepositoryModule: Module() {
    init {
        bind(IPokemonRepositoriy::class.java).toProvider(PokemonRepositoryProvider::class.java).providesSingletonInScope()
    }
}
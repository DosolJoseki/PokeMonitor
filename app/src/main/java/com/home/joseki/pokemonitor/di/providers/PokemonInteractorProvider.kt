package com.home.joseki.pokemonitor.di.providers

import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.interactors.PokemonInteractor
import com.home.joseki.pokemonitor.repositories.IPokemonRepositoriy
import javax.inject.Inject
import javax.inject.Provider

class PokemonInteractorProvider @Inject constructor(
    private val pokemonRepositoriy: IPokemonRepositoriy
): Provider<IPokemonInteractor> {
    override fun get(): IPokemonInteractor = PokemonInteractor(pokemonRepositoriy)
}
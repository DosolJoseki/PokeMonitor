package com.home.joseki.pokemonitor.di.providers

import com.home.joseki.pokemonitor.repositories.IPokemonRepositoriy
import com.home.joseki.pokemonitor.repositories.PokemonRepositoriy
import com.home.joseki.pokemonitor.web.api.IPokeApi
import javax.inject.Inject
import javax.inject.Provider

class PokemonRepositoryProvider @Inject constructor(
    private val pokeApi: IPokeApi
): Provider<IPokemonRepositoriy> {
    override fun get(): IPokemonRepositoriy = PokemonRepositoriy(pokeApi)
}
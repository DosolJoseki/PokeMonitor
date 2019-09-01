package com.home.joseki.pokemonitor.repositories

import com.home.joseki.pokemonitor.model.Pokemons
import com.home.joseki.pokemonitor.web.api.IPokeApi
import io.reactivex.Observable

class PokemonRepositoriy(
    private val pokeApi: IPokeApi
): IPokemonRepositoriy {

    companion object{
        private const val LIMIT_PER_SHEET = "30"
    }
    override fun getPokemons(offset: String): Observable<Pokemons> = pokeApi.getPokemons(offset, LIMIT_PER_SHEET)
}
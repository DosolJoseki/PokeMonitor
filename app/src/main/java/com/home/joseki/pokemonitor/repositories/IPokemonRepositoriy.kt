package com.home.joseki.pokemonitor.repositories

import com.home.joseki.pokemonitor.model.Pokemon
import com.home.joseki.pokemonitor.model.Pokemons
import io.reactivex.Observable

interface IPokemonRepositoriy {
    fun getPokemons(offset: String): Observable<Pokemons>
    fun getPokemonInfo(name: String): Observable<Pokemon>
}
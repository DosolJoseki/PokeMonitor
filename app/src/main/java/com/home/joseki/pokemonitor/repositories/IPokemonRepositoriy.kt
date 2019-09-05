package com.home.joseki.pokemonitor.repositories

import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.Observable
import io.reactivex.Single

interface IPokemonRepositoriy {
    fun getPokemons(offset: String): Single<List<Pokemon>>
    fun getPokemonInfo(name: String): Observable<Pokemon>
    fun getPokemonCount(): Int
}
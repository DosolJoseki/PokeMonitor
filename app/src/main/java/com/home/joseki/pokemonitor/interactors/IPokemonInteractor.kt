package com.home.joseki.pokemonitor.interactors

import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.Observable
import com.home.joseki.pokemonitor.model.Pokemons
import io.reactivex.subjects.PublishSubject

interface IPokemonInteractor {
    val onPokemonSelected: PublishSubject<Pokemon>
    val onPokemonUpdating: PublishSubject<Boolean>

    fun getPokemons(offset: String): Observable<Pokemons>
    fun getPokemonInfo(name: String): Observable<Pokemon>
}
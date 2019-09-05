package com.home.joseki.pokemonitor.interactors

import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface IPokemonInteractor {
    val onPokemonSelected: PublishSubject<Pokemon>
    val onPokemonUpdating: PublishSubject<Boolean>

    fun getPokemons(isRandom: Boolean): Single<List<Pokemon>>
    fun getPokemonInfo(name: String): Observable<Pokemon>
    fun getPokemonCount(): Int
    fun flush()
}
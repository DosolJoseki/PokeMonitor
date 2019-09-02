package com.home.joseki.pokemonitor.interactors

import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.Observable
import com.home.joseki.pokemonitor.model.Pokemons
import com.home.joseki.pokemonitor.repositories.IPokemonRepositoriy
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class PokemonInteractor @Inject constructor(
    val pokemonRepositoriy: IPokemonRepositoriy
): IPokemonInteractor {
    override val onPokemonSelected: PublishSubject<Pokemon> = PublishSubject.create()
    override val onPokemonUpdating: PublishSubject<Boolean> = PublishSubject.create()

    override fun getPokemons(offset: String): Observable<Pokemons> = pokemonRepositoriy.getPokemons(offset)
    override fun getPokemonInfo(name: String): Observable<Pokemon> = pokemonRepositoriy.getPokemonInfo("bulbasaur")
}
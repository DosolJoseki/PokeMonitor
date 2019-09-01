package com.home.joseki.pokemonitor.di.view.fragments.main

import io.reactivex.Observable
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemons

class MainFragmentPresenter(
    val pokemonInteractor: IPokemonInteractor,
    val view: MainFragment
) {

    init{
        view.setPokemons(pokemonInteractor.getPokemons("sdf"))
    }

    fun onPokemonSelected(): Observable<Pokemons> = pokemonInteractor.getPokemons("")
}
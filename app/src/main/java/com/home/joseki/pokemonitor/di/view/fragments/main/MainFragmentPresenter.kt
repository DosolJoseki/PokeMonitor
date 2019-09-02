package com.home.joseki.pokemonitor.di.view.fragments.main

import io.reactivex.Observable
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemons
import io.reactivex.android.schedulers.AndroidSchedulers

class MainFragmentPresenter(
    val pokemonInteractor: IPokemonInteractor,
    val view: MainFragment
) {

    companion object {
        private const val OFFSET_START_POSITION = "0"
        private lateinit var pokemons: Pokemons
    }

    init {
        pokemonInteractor.getPokemons(OFFSET_START_POSITION)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                pokemons = it
                //view.showUpdateProgress(false)
                //view.setPokemons(it)
            }
    }

    fun onPokemonSelected(): Observable<Pokemons> = pokemonInteractor.getPokemons("")
}
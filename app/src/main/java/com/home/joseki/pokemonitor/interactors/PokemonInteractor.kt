package com.home.joseki.pokemonitor.interactors

import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.Observable
import com.home.joseki.pokemonitor.repositories.IPokemonRepositoriy
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class PokemonInteractor @Inject constructor(
    private val pokemonRepositoriy: IPokemonRepositoriy
): IPokemonInteractor {
    companion object {
        private const val ITEMS_PER_SHEET = 30
        private const val STARTING_ITEM = 0
    }
    private var offset = STARTING_ITEM

    override val onPokemonSelected: PublishSubject<Pokemon> = PublishSubject.create()
    override val onPokemonUpdating: PublishSubject<Boolean> = PublishSubject.create()

    override fun getPokemonCount(): Int = pokemonRepositoriy.getPokemonCount()
    override fun getPokemons(isRandom: Boolean): Single<List<Pokemon>> {
        if(isRandom){
            offset = (STARTING_ITEM..pokemonRepositoriy.getPokemonCount() - ITEMS_PER_SHEET).random()
        }
        return getPokesAndAddSheet()
    }
    override fun getPokemonInfo(name: String): Observable<Pokemon> = pokemonRepositoriy.getPokemonInfo(name)
    override fun flush() {
        offset = STARTING_ITEM
    }
    private fun getPokesAndAddSheet(): Single<List<Pokemon>>{
        val list: Single<List<Pokemon>> = pokemonRepositoriy.getPokemons(offset.toString())
        offset += ITEMS_PER_SHEET
        return list
    }
}
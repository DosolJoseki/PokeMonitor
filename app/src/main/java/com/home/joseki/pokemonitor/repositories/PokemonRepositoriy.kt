package com.home.joseki.pokemonitor.repositories

import com.home.joseki.pokemonitor.model.Pokemon
import com.home.joseki.pokemonitor.model.Pokemons
import com.home.joseki.pokemonitor.web.api.IPokeApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class PokemonRepositoriy(
    private val pokeApi: IPokeApi
): IPokemonRepositoriy {

    companion object{
        private const val LIMIT_PER_SHEET = "30"
    }

    override fun getPokemonInfo(name: String): Observable<Pokemon> =
        pokeApi.getPokemonInfo(name)
            .subscribeOn(Schedulers.io())

    override fun getPokemons(offset: String): Observable<Pokemons> {
        val results: ArrayList<Pokemon> = ArrayList()

        return pokeApi.getPokemons(offset, LIMIT_PER_SHEET)
            .subscribeOn(Schedulers.io())
            .flatMap {poke ->
                Observable.fromIterable(poke.results)
                .flatMap {result ->
                    pokeApi.getPokemonInfo(result.name)
                 }
            }
            .doOnNext {
                results.add(it)
            }
            .doOnComplete { it }
            .flatMap { pokeApi.getPokemons(offset, LIMIT_PER_SHEET) }
    }

    private fun getPokemonInfos(pokemons: Pokemons): Observable<Pokemon> =
        Observable.fromIterable(pokemons.results)
            .flatMap { pokeApi.getPokemonInfo(it.name) }

}
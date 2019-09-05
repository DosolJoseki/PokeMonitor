package com.home.joseki.pokemonitor.repositories

import com.home.joseki.pokemonitor.model.Pokemon
import com.home.joseki.pokemonitor.model.Pokemons
import com.home.joseki.pokemonitor.web.api.IPokeApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class PokemonRepositoriy(
    private val pokeApi: IPokeApi
): IPokemonRepositoriy {
    private var count: Int = 0

    companion object{
        private const val LIMIT_PER_SHEET = "30"
    }

    override fun getPokemonCount(): Int = count

    override fun getPokemonInfo(name: String): Observable<Pokemon> =
        pokeApi.getPokemonInfo(name)
            .subscribeOn(Schedulers.io())

    override fun getPokemons(offset: String): Single<List<Pokemon>> =
        pokeApi.getPokemons(offset, LIMIT_PER_SHEET)
            .subscribeOn(Schedulers.io())
            .doOnNext { count = it.count }
            .flatMap {
                getPokemonInfos(it)
            }
            .toList()

    private fun getPokemonInfos(pokemons: Pokemons): Observable<Pokemon> =
        Observable.fromIterable(pokemons.results)
            .subscribeOn(Schedulers.io())
            .flatMap { pokeApi.getPokemonInfo(it.name) }
}
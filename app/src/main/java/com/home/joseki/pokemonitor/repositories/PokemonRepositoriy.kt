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
    companion object{
        private const val LIMIT_PER_SHEET = "30"
        private const val LIMIT = "10000"
    }

    override fun getPokemonCount(): Observable<Pokemons> =
        pokeApi.getPokemonCount(LIMIT)
            .subscribeOn(Schedulers.io())

    override fun getPokemonInfo(name: String): Observable<Pokemon> =
        pokeApi.getPokemonInfo(name)
            .subscribeOn(Schedulers.io())

    override fun getPokemons(offset: String): Single<List<Pokemon>> =
        pokeApi.getPokemons(offset, LIMIT_PER_SHEET)
            .subscribeOn(Schedulers.io())
            .flatMap {
                getPokemonInfos(it)
            }
            .toList()

    private fun getPokemonInfos(pokemons: Pokemons): Observable<Pokemon> =
        Observable.fromIterable(pokemons.results)
            .flatMap { pokeApi.getPokemonInfo(it.name) }
}
package com.home.joseki.pokemonitor.web.api

import com.home.joseki.pokemonitor.model.Pokemon
import com.home.joseki.pokemonitor.model.Pokemons
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IPokeApi {
    @GET("pokemon/bulbasaur")
    fun getPokemonInfo(
        @Query("name") name: String
    ): Observable<Pokemon>

    @GET("pokemon")
    fun getPokemons(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): Observable<Pokemons>
}
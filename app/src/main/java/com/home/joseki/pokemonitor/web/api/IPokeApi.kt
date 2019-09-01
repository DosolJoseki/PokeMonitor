package com.home.joseki.pokemonitor.web.api

import com.home.joseki.pokemonitor.model.Pokemon
import com.home.joseki.pokemonitor.model.Pokemons
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IPokeApi {
    @GET("pokemon")
    fun getPokemonInfo(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Observable<Pokemon>

    @GET("pokemon")
    fun getPokemons(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): Observable<Pokemons>
}
package com.home.joseki.pokemonitor.web.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class PokeApiProvider @Inject constructor(
    private val okHttpClient: OkHttpClient
) : Provider<IPokeApi> {

    companion object {
        private const val baseUrl: String = "https://pokeapi.co/api/v2/"
    }

    override fun get(): IPokeApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(IPokeApi::class.java)
}
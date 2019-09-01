package com.home.joseki.pokemonitor.di.api


import com.facebook.stetho.okhttp3.StethoInterceptor
import com.home.joseki.pokemonitor.web.api.IPokeApi
import com.home.joseki.pokemonitor.web.api.PokeApiProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import toothpick.config.Module

class ApiModule: Module() {
    init {
        bind(OkHttpClient::class.java).toInstance(
            OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build())
        bind(IPokeApi::class.java).toProvider(PokeApiProvider::class.java).providesSingletonInScope()
    }
}
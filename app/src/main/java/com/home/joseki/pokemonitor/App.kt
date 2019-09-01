package com.home.joseki.pokemonitor

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.home.joseki.pokemonitor.di.*
import com.home.joseki.pokemonitor.di.api.ApiModule
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment())
        }
        val appScope: Scope = Toothpick.openScope(Scopes.APP)
        appScope.installModules(AppModule(this), ApiModule(), ProviderModule(), RepositoryModule(), NavigationModule())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
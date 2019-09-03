package com.home.joseki.pokemonitor

import com.home.joseki.pokemonitor.di.view.navigation.MainRouter
import com.home.joseki.pokemonitor.di.view.navigation.Screens
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val router: MainRouter
) {
    private val compositeDisposable = CompositeDisposable()

    init {
        router.navigateTo(Screens.MainScreen)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

    fun onBackPressed(){
        router.backTo(Screens.MainScreen)
    }
}
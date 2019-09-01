package com.home.joseki.pokemonitor

import com.home.joseki.pokemonitor.di.view.navigation.MainRouter
import com.home.joseki.pokemonitor.di.view.navigation.Screens
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivityPresenter(
    private val view: MainActivity,
    private val router: MainRouter,
    private val pokemonInfoInteractor: IPokemonInteractor
) {
    private val compositeDisposable = CompositeDisposable()

    init {

        compositeDisposable.add(
            pokemonInfoInteractor.onPokemonUpdating
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showUpdateProgress(it)
                }
        )

        compositeDisposable.add(
            pokemonInfoInteractor.onPokemonSelected.subscribe {
                router.navigateTo(Screens.MainScreen)
                view.onPokemonSelected(it)
            })
        router.replaceScreen(Screens.MainScreen)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
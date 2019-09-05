package com.home.joseki.pokemonitor.fragments.main

import com.home.joseki.pokemonitor.di.navigation.Screens
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import io.reactivex.disposables.CompositeDisposable



class MainFragmentPresenter(
    private val view: MainFragment,
    private val router: Router,
    private val pokemonInteractor: IPokemonInteractor
) {

    private var compositeDisposable = CompositeDisposable()

    init {
        if(view.pokemonAdapter.itemCount == 0)
            getPokemons(isRandom = false, addToList = false)
    }

    fun onPokemonSelected(pokemon: Pokemon){
        router.navigateTo(Screens.PokemonInfoScreen(pokemon))
    }

    fun onRecyclerReachedLastElement(){
        getPokemons(isRandom = false, addToList = true)
    }

    fun onRefreshing(){
        pokemonInteractor.flush()
        getPokemons(isRandom = false, addToList = false)
    }

    fun onButtonClick(){
        getPokemons(isRandom = true, addToList = false)
    }

    private fun getPokemons(isRandom: Boolean, addToList: Boolean){
        compositeDisposable.add(
        pokemonInteractor.getPokemons(isRandom)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if(addToList){
                        view.addPokemons(it)
                    } else {
                        view.setPokemons(it) }
                    view.showUpdateProgress(false)
                },
                {
                    Timber.e(it)
                }
            ))
        view.showUpdateProgress(true)
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}
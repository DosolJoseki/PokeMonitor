package com.home.joseki.pokemonitor.fragments.main

import com.home.joseki.pokemonitor.di.navigation.Screens
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import io.reactivex.disposables.CompositeDisposable



class MainFragmentPresenter(
    val view: MainFragment,
    private val router: Router,
    private val pokemonInteractor: IPokemonInteractor
) {
    companion object {
        private const val ITEMS_PER_SHEET = 30
    }
    private var offset = 0
    private var compositeDisposable = CompositeDisposable()

    init {
        if(view.pokemonAdapter.itemCount == 0)
            getPokemons(offset, false)
    }

    fun onPokemonSelected(pokemon: Pokemon){
        router.navigateTo(Screens.PokemonInfoScreen(pokemon))
    }

    fun onRecyclerReachedLastElement(){
        offset += ITEMS_PER_SHEET
        getPokemons(offset, true)
    }

    fun onRefreshing(){
        offset = 0
        getPokemons(offset, false)
    }

    fun onButtonClick(){
        if(pokemonInteractor.getPokemonCount() >= 30){
            getPokemons((0..pokemonInteractor.getPokemonCount() - 30).random(), false)
            view.showUpdateProgress(true)
        }
    }

    private fun getPokemons(pos: Int, addToList: Boolean){
        compositeDisposable.add(
        pokemonInteractor.getPokemons(pos.toString())
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
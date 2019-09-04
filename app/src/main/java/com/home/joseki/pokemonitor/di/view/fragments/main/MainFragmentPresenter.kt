package com.home.joseki.pokemonitor.di.view.fragments.main

import com.home.joseki.pokemonitor.di.view.navigation.Screens
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable



class MainFragmentPresenter @Inject constructor(
    val view: MainFragment,
    private val router: Router,
    private val pokemonInteractor: IPokemonInteractor
) {
    companion object {
        private const val ITEMS_PER_SHEET = 30
    }
    private var offset = 0
    private var pokemonCount = 0
    var compositeDisposable = CompositeDisposable()

    fun initiate(){
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
        if(pokemonCount == 0){
            compositeDisposable.add(
            pokemonInteractor.getPokemonCount()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val rnd: Int
                    pokemonCount = it.count.toInt()
                    if(pokemonCount >= 30){
                        rnd = (0..pokemonCount-30).random()
                        getPokemons(rnd, false)
                    }
                })
        } else {
            val rnd: Int
            if(pokemonCount >= 30){
                rnd = (0..pokemonCount-30).random()
                getPokemons(rnd, false)
            }
        }
        view.showUpdateProgress(true)
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
package com.home.joseki.pokemonitor.di.view.fragments.main

import com.home.joseki.pokemonitor.di.view.navigation.Screens
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(
    val view: MainFragment,
    private val router: Router,
    private val pokemonInteractor: IPokemonInteractor
) {
    private var offset = 0

    fun initiate(){
        getPokemons(offset)
    }

    fun onPokemonSelected(pokemon: Pokemon){
        router.navigateTo(Screens.PokemonInfoScreen(pokemon))
    }

    fun onRecyclerReachedLastElement(){
        offset += 30
        getPokemons(offset)
    }

    fun onRefreshing(){
        offset = 0
        getPokemons(offset)
    }

    private fun getPokemons(pos: Int){
        pokemonInteractor.getPokemons(pos.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if(pos == 0){
                        view.setPokemons(it)
                    } else {
                        view.addPokemons(it)
                    }
                    view.showUpdateProgress(false)
                },
                {
                    Timber.e(it)
                }
            )
        view.showUpdateProgress(true)
    }
}
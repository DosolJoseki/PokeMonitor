package com.home.joseki.pokemonitor.di.view.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.di.Scopes
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemons
import toothpick.Toothpick

class MainFragment: Fragment() {
    private lateinit var presenter: MainFragmentPresenter
    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val scope = Toothpick.openScope(Scopes.APP)
        Toothpick.inject(this, scope)
        presenter = MainFragmentPresenter(scope.getInstance(IPokemonInteractor::class.java), this)

        return view
    }

    fun setPokemons(pokemons: Pokemons){
        //TODO
    }

    fun showUpdateProgress(boolean: Boolean){
        //TODO
    }
}
package com.home.joseki.pokemonitor.di.view.fragments.pokemoninfo

import androidx.fragment.app.Fragment
import com.home.joseki.pokemonitor.model.Pokemon

class PokemonInfoFragment: Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) = PokemonInfoFragment()
    }
}
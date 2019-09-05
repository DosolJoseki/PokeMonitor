package com.home.joseki.pokemonitor.di.navigation

import androidx.fragment.app.Fragment
import com.home.joseki.pokemonitor.fragments.main.MainFragment
import com.home.joseki.pokemonitor.fragments.pokemoninfo.PokemonInfoFragment
import com.home.joseki.pokemonitor.model.Pokemon
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object MainScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = MainFragment.newInstance()
    }

    data class PokemonInfoScreen(val pokemon: Pokemon) : SupportAppScreen() {
        override fun getFragment(): Fragment = PokemonInfoFragment.newInstance(pokemon)
    }
}
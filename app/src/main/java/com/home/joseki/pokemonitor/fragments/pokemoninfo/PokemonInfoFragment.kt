package com.home.joseki.pokemonitor.fragments.pokemoninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.home.joseki.pokemonitor.PropertiesGetter
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_info.view.*

class PokemonInfoFragment(
    val pokemon: Pokemon
): Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) =
            PokemonInfoFragment(pokemon)
        private const val STAT_NAME_HP = "hp"
        private const val STAT_NAME_ATK = "attack"
        private const val STAT_NAME_DEF = "defense"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_info, container, false)

        val tvPokemonName = view.tvPokemonName
        val tvPokemonHeight = view.tvHeight
        val tvPokemonWeight = view.tvWeight
        val tvPokemonType = view.tvType
        val tvPokemonHp = view.tvHP
        val tvPokemonAtk = view.tvATK
        val tvPokemonDef = view.tvDEF
        val ivPokemonImage = view.ivPokemonImage

        tvPokemonName.text = pokemon.name
        pokemon.sprites?.let {
            Picasso.get().load(it.frontDefault).into(ivPokemonImage)
        }
        tvPokemonHeight.text = pokemon.height
        tvPokemonWeight.text = pokemon.weight
        tvPokemonType.text = PropertiesGetter.getTypes(pokemon.types)
        tvPokemonHp.text = PropertiesGetter.getStat(STAT_NAME_HP, pokemon.stats).toString()
        tvPokemonAtk.text = PropertiesGetter.getStat(STAT_NAME_ATK, pokemon.stats).toString()
        tvPokemonDef.text = PropertiesGetter.getStat(STAT_NAME_DEF, pokemon.stats).toString()

        return view
    }
}
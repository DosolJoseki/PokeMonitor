package com.home.joseki.pokemonitor.di.view.fragments.pokemoninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.home.joseki.pokemonitor.PropertiesGetter
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonInfoFragment(
    val pokemon: Pokemon
): Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) = PokemonInfoFragment(pokemon)
        private const val STAT_NAME_HP = "hp"
        private const val STAT_NAME_ATK = "attack"
        private const val STAT_NAME_DEF = "defense"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_info, container, false)

        val tvPokemonName = view.findViewById<TextView>(R.id.tvPokemonName)
        val tvPokemonHeight = view.findViewById<TextView>(R.id.tvHeight)
        val tvPokemonWeight = view.findViewById<TextView>(R.id.tvWeight)
        val tvPokemonType = view.findViewById<TextView>(R.id.tvType)
        val tvPokemonHp = view.findViewById<TextView>(R.id.tvHP)
        val tvPokemonAtk = view.findViewById<TextView>(R.id.tvATK)
        val tvPokemonDef = view.findViewById<TextView>(R.id.tvDEF)
        val ivPokemonImage = view.findViewById<ImageView>(R.id.ivPokemonImage)

        tvPokemonName.text = pokemon.name
        Picasso.get().load(pokemon.sprites!!.frontDefault).into(ivPokemonImage)
        tvPokemonHeight.text = pokemon.height
        tvPokemonWeight.text = pokemon.weight
        tvPokemonType.text = PropertiesGetter.getTypes(pokemon.types)
        tvPokemonHp.text = PropertiesGetter.getStat(STAT_NAME_HP, pokemon.stats)
        tvPokemonAtk.text = PropertiesGetter.getStat(STAT_NAME_ATK, pokemon.stats)
        tvPokemonDef.text = PropertiesGetter.getStat(STAT_NAME_DEF, pokemon.stats)

        return view
    }
}
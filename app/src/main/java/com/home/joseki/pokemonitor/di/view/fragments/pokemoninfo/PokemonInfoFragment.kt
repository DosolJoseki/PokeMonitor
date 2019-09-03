package com.home.joseki.pokemonitor.di.view.fragments.pokemoninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.di.view.navigation.Screens
import com.home.joseki.pokemonitor.model.Pokemon
import com.home.joseki.pokemonitor.model.Stat
import com.home.joseki.pokemonitor.model.Stats
import com.home.joseki.pokemonitor.model.Types
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

class PokemonInfoFragment(
    val pokemon: Pokemon
): Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) = PokemonInfoFragment(pokemon)
        private const val STARTING_INDEX = 2
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
        tvPokemonType.text = getTypes(pokemon.types)
        tvPokemonHp.text = getStat(STAT_NAME_HP, pokemon.stats)
        tvPokemonAtk.text = getStat(STAT_NAME_ATK, pokemon.stats)
        tvPokemonDef.text = getStat(STAT_NAME_DEF, pokemon.stats)

        return view
    }

    private fun getTypes(listtypes: List<Types>?): String {
        if (listtypes == null) {
            return ""
        }

        val stringBuilder = StringBuilder()

        for (types: Types in listtypes) {
            types.type?.let {
                stringBuilder.append(", ")
                stringBuilder.append (it.name)
            }
        }

        return stringBuilder.toString().substring(STARTING_INDEX)
    }

    private fun getStat(statName: String, statList: List<Stats>?): String {
        if (statList == null) {
            return ""
        }

        for(stat: Stats in statList){
            stat.stat?.let {
                if (stat.stat.name == statName)
                    return stat.baseStat
            }
        }

        return ""
    }
}
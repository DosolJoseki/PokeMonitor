package com.home.joseki.pokemonitor.comparators

import com.home.joseki.pokemonitor.PropertiesGetter
import com.home.joseki.pokemonitor.model.Pokemon

class PokemonHpComparator: Comparator<Pokemon> {
    companion object {
        private const val STAT_NAME_HP = "hp"
    }
    override fun compare(o1: Pokemon, o2: Pokemon): Int {
        return PropertiesGetter.getStatToInt(STAT_NAME_HP, o2.stats).compareTo(PropertiesGetter.getStatToInt(STAT_NAME_HP, o1.stats))
    }
}
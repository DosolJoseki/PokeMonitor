package com.home.joseki.pokemonitor.comparators

import com.home.joseki.pokemonitor.PropertiesGetter
import com.home.joseki.pokemonitor.model.Pokemon

class PokemonDefComparator: Comparator<Pokemon> {
    companion object {
        private const val STAT_NAME_DEF = "defense"
    }
    override fun compare(o1: Pokemon, o2: Pokemon): Int {
        return PropertiesGetter.getStatToInt(STAT_NAME_DEF, o2.stats).compareTo(PropertiesGetter.getStatToInt(STAT_NAME_DEF, o1.stats))
    }
}
package com.home.joseki.pokemonitor

import com.home.joseki.pokemonitor.model.Stats
import com.home.joseki.pokemonitor.model.Types

object PropertiesGetter {
    fun getStat(statName: String, statList: List<Stats>?): Int {
        return statList
            ?.map { it }
            ?.firstOrNull { it.stat?.name == statName }
            ?.baseStat
            ?:0
    }

    fun getTypes(listtypes: List<Types>?): String {
        return listtypes
            ?.mapNotNull { it.type }
            ?.joinToString(", ") { it.name }
            ?:""
    }
}
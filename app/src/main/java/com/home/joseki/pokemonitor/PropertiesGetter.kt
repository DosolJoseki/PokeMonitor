package com.home.joseki.pokemonitor

import com.home.joseki.pokemonitor.model.Stats
import com.home.joseki.pokemonitor.model.Types
import java.lang.StringBuilder

object PropertiesGetter {
    fun getStatToInt(statName: String, statList: List<Stats>?): Int {
        if (statList == null) {
            return 0
        }

        for(stat: Stats in statList){
            stat.stat?.let {
                if (stat.stat.name == statName)
                    return stat.baseStat.toInt()
            }
        }

        return 0
    }

    fun getStat(statName: String, statList: List<Stats>?): String {
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

    fun getTypes(listtypes: List<Types>?): String {
        if (listtypes == null) {
            return ""
        }

        val stringBuilder = StringBuilder()

        for (types: Types in listtypes) {
            types.type?.let {
                stringBuilder.append(", ")
                stringBuilder.append(types.type.name)
            }
        }

        return stringBuilder.toString().substring(2)
    }
}
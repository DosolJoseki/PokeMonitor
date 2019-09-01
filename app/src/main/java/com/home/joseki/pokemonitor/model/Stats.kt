package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("stat")
    val stat: Stat? = null,
    @SerializedName("base_stat")
    val baseStat: String = "",
    @SerializedName("effort")
    val effort: String = ""
) {
    //override fun toString(): String {
    //    return "ClassPojo [stat = $stat, base_stat = $base_stat, effort = $effort]"
    //}
}
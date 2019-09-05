package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("stat")
    val stat: Stat? = null,
    @SerializedName("base_stat")
    val baseStat: Int = 0,
    @SerializedName("effort")
    val effort: String = ""
)
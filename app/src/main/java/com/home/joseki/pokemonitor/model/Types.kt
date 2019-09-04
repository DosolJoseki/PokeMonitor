package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Types(
    @SerializedName("slot")
    val slot: String = "",
    @SerializedName("type")
    val type: Type? = null
)
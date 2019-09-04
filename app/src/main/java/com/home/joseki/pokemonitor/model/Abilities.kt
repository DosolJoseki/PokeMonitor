package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Abilities(
    @SerializedName("is_hidden")
    val isHidden: String = "",
    @SerializedName("ability")
    val ability: Ability? = null,
    @SerializedName("slot")
    val slot: String = ""
)
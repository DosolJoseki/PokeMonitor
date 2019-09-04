package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class GameIndices(
    @SerializedName("game_index")
    val gameIndex: String = "",
    @SerializedName("version")
    val version: Version? = null
)
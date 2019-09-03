package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class VersionDetails(
    @SerializedName("version")
    val version: Version? = null,
    @SerializedName("rarity")
    val rarity: String = ""
)
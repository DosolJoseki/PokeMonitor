package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class HeldItems(
    @SerializedName("item")
    val item: Item? = null,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetails>? = null
)
package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Moves(
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetails>? = null,
    @SerializedName("move")
    val move: Move? = null
)
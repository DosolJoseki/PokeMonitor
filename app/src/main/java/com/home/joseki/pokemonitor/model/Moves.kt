package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Moves(
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetails>? = null,
    @SerializedName("move")
    val move: Move? = null
) {
    //override fun toString(): String {
    //    return "ClassPojo [version_group_details = $version_group_details, move = $move]"
    //}
}
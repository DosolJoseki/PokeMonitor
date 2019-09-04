package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class VersionGroupDetails(
    @SerializedName("level_learned_at")
    val levelLearnedAt: String = "",
    @SerializedName("version_group")
    val versionGroup: VersionGroup? = null,
    @SerializedName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod? = null
)
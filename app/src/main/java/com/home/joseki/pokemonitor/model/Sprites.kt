package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("back_shiny_female")
    val backShinyFemale: String? = null,
    @SerializedName("back_female")
    val backFemale: String? = null,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null,
    @SerializedName("front_female")
    val frontFemale: String? = null,
    @SerializedName("back_default")
    val backDefault: String = "",
    @SerializedName("front_default")
    val frontDefault: String = "",
    @SerializedName("back_shiny")
    val backShiny: String = "",
    @SerializedName("front_shiny")
    val frontShiny: String = ""
)
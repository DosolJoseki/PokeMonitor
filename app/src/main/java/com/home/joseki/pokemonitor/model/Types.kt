package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Types(
    @SerializedName("slot")
    val slot: String = "",
    @SerializedName("type")
    val type: Type? = null
) {
    //override fun toString(): String {
    //    return "ClassPojo [slot = $slot, type = $type]"
    //}
}
package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class MoveLearnMethod(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = ""
) {
    //override fun toString(): String {
    //    return "ClassPojo [name = $name, url = $url]"
    //}
}
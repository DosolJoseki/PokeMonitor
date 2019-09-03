package com.home.joseki.pokemonitor.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("location_area_encounters")
    val LocationAreaEncounters: String = "",
    @SerializedName("types")
    val types: List<Types>? = null,
    @SerializedName("base_experience")
    val baseExperience: String = "",
    @SerializedName("held_items")
    val heldItems: List<HeldItems>? = null,
    @SerializedName("weight")
    val weight: String = "",
    @SerializedName("is_default")
    val isDefault: String = "",
    @SerializedName("sprites")
    val sprites: Sprites? = null,
    @SerializedName("abilities")
    val abilities: List<Abilities>? = null,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndices>? = null,
    @SerializedName("species")
    val species: Species? = null,
    @SerializedName("stats")
    val stats: List<Stats>? = null,
    @SerializedName("moves")
    val moves: List<Moves>? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("forms")
    val forms: List<Forms>? = null,
    @SerializedName("height")
    val height: String = "",
    @SerializedName("order")
    val order: String = ""

) {
    //override fun toString(): String {
    //    return "ClassPojo [location_area_encounters = $location_area_encounters, types = $types, base_experience = $base_experience, held_items = $held_items, weight = $weight, is_default = $is_default, sprites = $sprites, abilities = $abilities, game_indices = $game_indices, species = $species, stats = $stats, moves = $moves, name = $name, id = $id, forms = $forms, height = $height, order = $order]"
    //}
}
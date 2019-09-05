package com.home.joseki.pokemonitor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.joseki.pokemonitor.PropertiesGetter
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.model.Pokemon
import com.squareup.picasso.Picasso
import com.home.joseki.pokemonitor.adapters.PokemonListAdapter.PokemonViewHolder
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.list_item_pokemon.view.*
import kotlin.collections.ArrayList


class PokemonListAdapter: RecyclerView.Adapter<PokemonViewHolder>() {
    private var items: ArrayList<Pokemon> = ArrayList()
    var needHPCheck: Boolean = false
    var needAtkCheck: Boolean = false
    var needDefCheck: Boolean = false

    val itemClickListener: PublishSubject<Pokemon> = PublishSubject.create()

    companion object {
        private const val STAT_NAME_ATK = "attack"
        private const val STAT_NAME_DEF = "defense"
        private const val STAT_NAME_HP = "hp"
    }

    fun notifyPokeItems(){
        sort()
        notifyDataSetChanged()
    }

    fun setItems(i: ArrayList<Pokemon>) {
        items.clear()
        items.addAll(i)
        sort()
        notifyDataSetChanged()
    }

    fun addItems(i: ArrayList<Pokemon>){
        items.addAll(i)
        sort()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_pokemon, parent, false))

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) = holder.bind(items[position])

    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon){
            itemView.setOnClickListener { itemClickListener.onNext(pokemon) }

            val pokemonName = itemView.tvPokemonName
            val pokemonType = itemView.tvPokemonType
            val pokemonPicture = itemView.ivPokemonImage

            pokemonName.text = pokemon.name
            pokemonType.text = PropertiesGetter.getTypes(pokemon.types)
            pokemon.sprites?.let {
                Picasso.get().load(it.frontDefault).into(pokemonPicture)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    private fun sort(){
        when {
            needHPCheck and needAtkCheck and needDefCheck  -> {
                items = ArrayList(items.sortedWith(
                    compareBy(
                        {PropertiesGetter.getStat(STAT_NAME_HP, it.stats)},
                        {PropertiesGetter.getStat(STAT_NAME_ATK, it.stats)},
                        {PropertiesGetter.getStat(STAT_NAME_DEF, it.stats)})).asReversed())
            }
            needHPCheck and needAtkCheck -> {
                items = ArrayList(items.sortedWith(
                    compareBy(
                        {PropertiesGetter.getStat(STAT_NAME_HP, it.stats)},
                        {PropertiesGetter.getStat(STAT_NAME_ATK, it.stats)})).asReversed())
            }
            needHPCheck and needDefCheck -> {
                items = ArrayList(items.sortedWith(
                    compareBy(
                        {PropertiesGetter.getStat(STAT_NAME_HP, it.stats)},
                        {PropertiesGetter.getStat(STAT_NAME_DEF, it.stats)})).asReversed())
            }
            needAtkCheck and needDefCheck -> {
                items = ArrayList(items.sortedWith(
                    compareBy(
                        {PropertiesGetter.getStat(STAT_NAME_ATK, it.stats)},
                        {PropertiesGetter.getStat(STAT_NAME_DEF, it.stats)})).asReversed())
            }
            needHPCheck -> {
                items = ArrayList(items.sortedWith(
                    compareBy {PropertiesGetter.getStat(STAT_NAME_HP, it.stats)}).asReversed())
            }
            needAtkCheck -> {
                items = ArrayList(items.sortedWith(
                    compareBy {PropertiesGetter.getStat(STAT_NAME_ATK, it.stats)}).asReversed())
            }
            needDefCheck -> {
                items = ArrayList(items.sortedWith(
                    compareBy {PropertiesGetter.getStat(STAT_NAME_DEF, it.stats)}).asReversed())
            }
            else -> {
                items = ArrayList(items.sortedWith(
                    compareBy{it.id}
                ))
            }
        }
    }
}
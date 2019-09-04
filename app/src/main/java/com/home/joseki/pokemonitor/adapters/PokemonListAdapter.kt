package com.home.joseki.pokemonitor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.home.joseki.pokemonitor.PropertiesGetter
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.model.Pokemon
import com.squareup.picasso.Picasso
import com.home.joseki.pokemonitor.adapters.PokemonListAdapter.PokemonViewHolder
import com.home.joseki.pokemonitor.comparators.PokemonAtkComparator
import com.home.joseki.pokemonitor.comparators.PokemonDefComparator
import com.home.joseki.pokemonitor.comparators.PokemonHpComparator
import io.reactivex.subjects.PublishSubject
import java.util.*
import kotlin.collections.ArrayList


class PokemonListAdapter: RecyclerView.Adapter<PokemonViewHolder>() {
    private var items: ArrayList<Pokemon> = ArrayList()
    private var itemsDefault: ArrayList<Pokemon> = ArrayList()
    var needHPCheck: Boolean = false
    var needAtkCheck: Boolean = false
    var needDefCheck: Boolean = false

    val itemClickListener: PublishSubject<Pokemon> = PublishSubject.create()

    fun notifyPokeItems(){
        sort()
        notifyDataSetChanged()
    }

    fun setItems(i: ArrayList<Pokemon>) {
        items.clear()
        items.addAll(i)
        itemsDefault.clear()
        itemsDefault.addAll(items)
        sort()
        notifyDataSetChanged()
    }

    fun addItems(i: ArrayList<Pokemon>){
        items.addAll(i)
        itemsDefault.clear()
        itemsDefault.addAll(items)
        sort()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_pokemon, parent, false))

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) = holder.bind(items[position])

    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon){
            itemView.setOnClickListener { itemClickListener.onNext(pokemon) }

            val pokemonName = itemView.findViewById<TextView>(R.id.tvPokemonName)
            val pokemonType = itemView.findViewById<TextView>(R.id.tvPokemonType)
            val pokemonPicture = itemView.findViewById<ImageView>(R.id.ivPokemonImage)

            pokemonName.text = pokemon.name
            pokemonType.text = PropertiesGetter.getTypes(pokemon.types)
            Picasso.get().load(pokemon.sprites!!.frontDefault).into(pokemonPicture)
        }
    }

    override fun getItemCount(): Int = items.size

    private fun sort(){
        if(needHPCheck){
            Collections.sort(items, PokemonHpComparator())
        }
        if(needAtkCheck){
            Collections.sort(items, PokemonAtkComparator())
        }
        if(needDefCheck){
            Collections.sort(items, PokemonDefComparator())
        }
        if(!needHPCheck and !needAtkCheck and !needDefCheck){
            items.clear()
            items.addAll(itemsDefault)
        }
    }
}
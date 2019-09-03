package com.home.joseki.pokemonitor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.model.Pokemon
import com.squareup.picasso.Picasso
import com.home.joseki.pokemonitor.adapters.PokemonListAdapter.PokemonViewHolder
import com.home.joseki.pokemonitor.model.Types
import io.reactivex.subjects.PublishSubject
import java.lang.StringBuilder


class PokemonListAdapter: RecyclerView.Adapter<PokemonViewHolder>() {
    companion object{
        private const val STARTING_INDEX = 2
    }

    private val items: ArrayList<Pokemon> = ArrayList()

    val itemClickListener: PublishSubject<Pokemon> = PublishSubject.create()

    fun setItems(i: ArrayList<Pokemon>) {
        items.clear()
        items.addAll(i)
        notifyDataSetChanged()
    }

    fun addItems(i: ArrayList<Pokemon>){
        items.addAll(i)
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
            pokemonType.text = getTypes(pokemon.types)
            Picasso.get().load(pokemon.sprites!!.frontDefault).into(pokemonPicture)
        }
    }

    override fun getItemCount(): Int = items.size

    private fun getTypes(listtypes: List<Types>?): String {
        if (listtypes == null) {
            return ""
        }

        val stringBuilder = StringBuilder()

        for (types: Types in listtypes) {
            types.type?.let {
                stringBuilder.append(", ")
                stringBuilder.append(types.type.name)
            }
        }

        return stringBuilder.toString().substring(STARTING_INDEX)
    }
}
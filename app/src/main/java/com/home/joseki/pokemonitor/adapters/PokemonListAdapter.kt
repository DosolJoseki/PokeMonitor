package com.home.joseki.pokemonitor.adapters


import android.content.Context
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
import io.reactivex.subjects.PublishSubject


class PokemonListAdapter: RecyclerView.Adapter<PokemonViewHolder>() {

    private val items: ArrayList<Pokemon> = ArrayList()

    val itemClickListener: PublishSubject<Pokemon> = PublishSubject.create()

    fun setItems(i: ArrayList<Pokemon>) {
        items.clear()
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
            pokemonType.text = pokemon.height
            Picasso.get().load(pokemon.sprites!!.frontDefault).into(pokemonPicture)
        }
    }

    override fun getItemCount(): Int = items.size
}
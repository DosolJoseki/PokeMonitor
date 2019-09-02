package com.home.joseki.pokemonitor.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.model.Pokemon
import com.squareup.picasso.Picasso


class PokemonListAdapter(
    context: Context,
    val resource: Int,
    val list: List<Pokemon>
) : ArrayAdapter<Pokemon>(context, resource, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val pokemon = list[position]
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val single_row = inflater.inflate(R.layout.list_item_pokemon, parent, false)

        val pokemonName = single_row.findViewById<TextView>(R.id.tvPokemonName)
        val pokemonType = single_row.findViewById<TextView>(R.id.tvPokemonType)
        val pokemonPicture = single_row.findViewById<ImageView>(R.id.ivPokemonImage)

        pokemonName.text = pokemon.name
        pokemonType.text = pokemon.height
        Picasso.get().load(pokemon.sprites!!.frontDefault).into(pokemonPicture)

        return single_row
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

    override fun getCount(): Int {
        return list.size
    }
}
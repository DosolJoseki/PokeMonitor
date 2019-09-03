package com.home.joseki.pokemonitor.di.view.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.adapters.PokemonListAdapter
import com.home.joseki.pokemonitor.di.Scopes
import com.home.joseki.pokemonitor.di.view.navigation.MainRouter
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemon
import io.reactivex.disposables.CompositeDisposable
import toothpick.Toothpick

class MainFragment: Fragment() {
    private lateinit var presenter: MainFragmentPresenter
    private var pokemonAdapter: PokemonListAdapter = PokemonListAdapter()
    private lateinit var updateProgress: SwipeRefreshLayout
    private val disposable = CompositeDisposable()

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()

        private const val DIRECTION_DOWN = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        updateProgress = view.findViewById(R.id.updateProgress)
        updateProgress.setOnRefreshListener {
            presenter.onRefreshing()
        }

        val scope = Toothpick.openScope(Scopes.APP)
        Toothpick.inject(this, scope)
        presenter = MainFragmentPresenter(this, scope.getInstance(MainRouter::class.java), scope.getInstance(IPokemonInteractor::class.java))

        val rvPokemonList = view.findViewById<RecyclerView>(R.id.rvPokemonList)
        rvPokemonList.layoutManager = LinearLayoutManager(view.context)
        rvPokemonList.adapter = pokemonAdapter

        rvPokemonList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(DIRECTION_DOWN)) {
                    presenter.onRecyclerReachedLastElement()
                }
            }
        })

        disposable.add(
            pokemonAdapter.itemClickListener.doOnNext {
                presenter.onPokemonSelected(it)
            }.subscribe()
        )

        if(pokemonAdapter.itemCount == 0){
            presenter.initiate()
        }

        return view
    }

    fun setPokemons(pokemons: List<Pokemon>){
        pokemonAdapter.setItems(ArrayList(pokemons))
    }

    fun addPokemons(pokemons: List<Pokemon>){
        pokemonAdapter.addItems(ArrayList(pokemons))
    }

    fun showUpdateProgress(boolean: Boolean){
        updateProgress.isRefreshing = boolean
    }
}
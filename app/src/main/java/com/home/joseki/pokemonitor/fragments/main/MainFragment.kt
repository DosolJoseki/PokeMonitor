package com.home.joseki.pokemonitor.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.home.joseki.pokemonitor.R
import com.home.joseki.pokemonitor.adapters.PokemonListAdapter
import com.home.joseki.pokemonitor.di.Scopes
import com.home.joseki.pokemonitor.interactors.IPokemonInteractor
import com.home.joseki.pokemonitor.model.Pokemon
import com.home.joseki.pokemonitor.di.navigation.MainRouter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_main.*
import toothpick.Toothpick
import kotlin.collections.ArrayList

class MainFragment: Fragment() {
    private lateinit var presenter: MainFragmentPresenter
    var pokemonAdapter: PokemonListAdapter = PokemonListAdapter()
    private val disposable = CompositeDisposable()

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()

        private const val DIRECTION_DOWN = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateProgress.setOnRefreshListener {
            presenter.onRefreshing()
        }

        val scope = Toothpick.openScope(Scopes.APP)
        Toothpick.inject(this, scope)
        presenter = MainFragmentPresenter(
            this,
            scope.getInstance(MainRouter::class.java),
            scope.getInstance(IPokemonInteractor::class.java)
        )

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

        randomPokemon.setOnClickListener {
            presenter.onButtonClick()
        }

        cbHp.setOnCheckedChangeListener{
                _,
                isChecked -> pokemonAdapter.needHPCheck = isChecked
            pokemonAdapter.notifyPokeItems()
        }

        cbAtk.setOnCheckedChangeListener{
                _,
                isChecked -> pokemonAdapter.needAtkCheck = isChecked
            pokemonAdapter.notifyPokeItems()
        }

        cbDef.setOnCheckedChangeListener{
                _,
                isChecked -> pokemonAdapter.needDefCheck = isChecked
            pokemonAdapter.notifyPokeItems()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}

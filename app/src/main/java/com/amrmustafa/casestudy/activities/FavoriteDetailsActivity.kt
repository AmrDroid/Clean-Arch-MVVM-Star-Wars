package com.amrmustafa.casestudy.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amrmustafa.casestudy.R
import com.amrmustafa.casestudy.models.*
import com.amrmustafa.casestudy.utils.Constants
import com.amrmustafa.casestudy.viewmodel.FavoriteDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.amrmustafa.casestudy.adapters.FilmsAdapter
import com.amrmustafa.casestudy.adapters.SpeciesAdapter
import com.amrmustafa.casestudy.databinding.ActivityDetailBinding
import com.amrmustafa.casestudy.models.FavoritePresentation
import com.amrmustafa.casestudy.utils.remove
import com.amrmustafa.casestudy.utils.show
import com.amrmustafa.casestudy.utils.showSnackbar

internal class FavoriteDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val filmsAdapter = FilmsAdapter()

    private val speciesAdapter = SpeciesAdapter()

    private var isFavorite = false

    private val favoritesViewModel by viewModel<FavoriteDetailViewModel>()

    protected var characterName = ""

    protected var favoritePresentation: FavoritePresentation? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val favorite =
            intent.getParcelableExtra<FavoritePresentation>(Constants.FAVORITE_KEY)

        favorite?.let { favoritePresentation ->
            bindFavorite(favoritePresentation)
            characterName = favoritePresentation.characterPresentation.name
            this.favoritePresentation = favoritePresentation
            checkIfFavorite()
            invalidateOptionsMenu()
        }

        observeFavoriteViewState()

        setupToolbar()
        super.onCreate(savedInstanceState)
    }

    fun bindFavorite(favoritePresentation: FavoritePresentation) {
        bindCharacterBasicInfo(favoritePresentation.characterPresentation)
        bindPlanet(favoritePresentation.planetPresentation)
        bindSpecies(favoritePresentation.speciePresentation)
        bindFilms(favoritePresentation.films)
    }

    val rootViewGroup: ViewGroup
        get() = binding.detailsLayout

    private fun setupToolbar() {
        setSupportActionBar(binding.detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun bindCharacterBasicInfo(character: CharacterPresentation?) {
        supportActionBar?.title = character?.name ?: ""
        binding.infoLayout.character = character
    }

    fun bindFilms(films: List<FilmPresentation>?) {
        films?.let {
            with(binding.filmsLayout) {
                filmsProgressBar.remove()
                detailsFilmsRecyclerView.apply {
                    adapter = filmsAdapter.apply { submitList(films) }
                }
            }
        }
    }

    fun bindSpecies(species: List<SpeciePresentation>?) {
        species?.let {
            with(binding.specieLayout) {
                speciesProgressBar.remove()
                if (species.isNotEmpty()) {
                    detailsSpeciesRecyclerView.apply {
                        adapter = speciesAdapter.apply { submitList(species) }
                    }
                } else noSpeciesTextView.show()
            }
        }
    }

    fun bindPlanet(planet: PlanetPresentation?) {
        planet?.let {
            with(binding.planetLayout) {
                planetProgressBar.remove()
                this.planet = planet
                detailsPlanetNameTextView.show()
                detailsPlanetPopulationTextView.show()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorites_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val menuItem = menu?.getItem(0)
        if (isFavorite)
            menuItem?.setIcon(R.drawable.ic_favs_24dp)
        else
            menuItem?.setIcon(R.drawable.ic_no_favs_24dp)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_alter_favorites -> {
                if (isFavorite) {
                    removeFromFavorites()
                    isFavorite = !isFavorite
                } else {
                    favoritePresentation?.let { favorite ->
                        addToFavorites(favorite)
                        isFavorite = !isFavorite
                    }
                }
                invalidateOptionsMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun checkIfFavorite() {
        favoritesViewModel.getFavorite(characterName)
    }

    private fun addToFavorites(favorite: FavoritePresentation) {
        favoritesViewModel.saveFavorite(favorite)
        showSnackbar(
            rootViewGroup,
            getString(R.string.info_added_to_favs)
        )
    }

    private fun removeFromFavorites() {
        favoritesViewModel.deleteFavorite(characterName)
        showSnackbar(
            rootViewGroup,
            getString(R.string.info_removed_from_favs)
        )
    }

    private fun observeFavoriteViewState() {
        favoritesViewModel.favoriteViewState.observe(this, Observer {
            isFavorite = it.isFavorite
            invalidateOptionsMenu()
            it.error?.let { e ->
                showSnackbar(rootViewGroup, getString(e.message))
            }
        })
    }
}

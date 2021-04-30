package com.amrmustafa.casestudy.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.amrmustafa.casestudy.R
import com.amrmustafa.casestudy.adapters.FilmsAdapter
import com.amrmustafa.casestudy.adapters.SpeciesAdapter
import com.amrmustafa.casestudy.models.*
import com.amrmustafa.casestudy.databinding.ActivityDetailBinding
import com.amrmustafa.casestudy.utils.*
import com.amrmustafa.casestudy.utils.Constants
import com.amrmustafa.casestudy.utils.showSnackbar
import com.amrmustafa.casestudy.viewmodel.FavoriteDetailViewModel
import com.amrmustafa.casestudy.viewmodel.DetailViewModel
import com.amrmustafa.casestudy.models.FavoritePresentation

internal class CharacterDetailsActivity : BaseActivity() {
    private val characterDetailViewModel by viewModel<DetailViewModel>()

    private val favoritesViewModel by viewModel<FavoriteDetailViewModel>()

    private lateinit var binding: ActivityDetailBinding

    private val filmsAdapter = FilmsAdapter()

    private val speciesAdapter = SpeciesAdapter()

    private var isFavorite = false

    protected var characterName = ""

    protected var favoritePresentation: FavoritePresentation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val favorite =
            intent.getParcelableExtra<FavoritePresentation>(Constants.FAVORITE_KEY)

        favorite?.let { favoritePresentation ->
            characterName = favoritePresentation.characterPresentation.name

            this.favoritePresentation = favoritePresentation

            checkIfFavorite()

            invalidateOptionsMenu()
        }

        observeFavoriteViewState()


        setSupportActionBar(binding.detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val character =
            intent.getParcelableExtra<CharacterPresentation>(Constants.CHARACTER_KEY)

        if (character == null) {
            characterDetailViewModel
                .displaycharacterError(R.string.error_loading_details)
        } else {
            characterName = character.name
            characterDetailViewModel.initView(character)
            checkIfFavorite()
            observeNetworkChanges(character.url)
            characterDetailViewModel.getCharacterDetails(character.url)
            observeFavoritePresentationCreationFromRemote()
        }

        observeDetailViewState()
    }

    val rootViewGroup: ViewGroup
        get() = binding.detailsLayout

    private fun observeDetailViewState() {
        characterDetailViewModel.detailViewState.observe(this, Observer {
            bindCharacterBasicInfo(it.info)
            bindSpecies(it.specie)
            bindFilms(it.films)
            bindPlanet(it.planet)
            it.error?.let { e ->
                onError(
                    resources.getString(e.message),
                    it.films.isNullOrEmpty(),
                    it.planet?.name.isNullOrEmpty(),
                    it.specie.isNullOrEmpty()
                )
            }

            if (it.isComplete) {
                showSnackbar(
                    binding.detailsLayout,
                    getString(R.string.info_loading_complete)
                )
                characterDetailViewModel.createFavoritePresentationFromRemoteCharacter()
            }
        })
    }

    private fun observeFavoritePresentationCreationFromRemote() {
        characterDetailViewModel.remoteToFavoritePresentation
            .observe(this, Observer { favPresentation ->
                favoritePresentation = favPresentation
            })
    }

    private fun onError(
        message: String,
        filmsStatus: Boolean,
        planetStatus: Boolean,
        specieStatus: Boolean
    ) {
        if (filmsStatus) {
            binding.filmsLayout.filmsProgressBar.hide()
            binding.filmsLayout.filmsErrorTextView.show()
        }
        if (planetStatus) {
            binding.planetLayout.planetProgressBar.hide()
            binding.planetLayout.planetErrorTextView.show()
        }
        if (specieStatus) {
            binding.specieLayout.speciesProgressBar.hide()
            binding.specieLayout.specieErrorTextView.show()
        }

        showSnackbar(binding.detailsLayout, message, isError = true)
    }

    private fun onErrorResolved() {
        binding.filmsLayout.filmsErrorTextView.remove()
        binding.planetLayout.planetErrorTextView.remove()
        binding.specieLayout.specieErrorTextView.remove()
        binding.filmsLayout.filmsProgressBar.show()
        binding.planetLayout.planetProgressBar.show()
        binding.specieLayout.speciesProgressBar.show()
    }

    private fun observeNetworkChanges(characterUrl: String) {
        onNetworkChange { isConnected ->
            characterDetailViewModel.detailViewState.value?.let { viewState ->
                if (isConnected && viewState.error != null) {
                    onErrorResolved()
                    characterDetailViewModel.getCharacterDetails(characterUrl, isRetry = true)
                }
            }
        }
    }

    fun bindCharacterBasicInfo(character: CharacterPresentation?) {
        supportActionBar?.title = character?.name ?: ""
        binding.infoLayout.character = character
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

    fun bindFilms(films: List<FilmPresentation>?) {
        films?.let {
            with(binding.filmsLayout) {
                filmsProgressBar.remove()
                if (films.isNotEmpty()) {

                    detailsFilmsRecyclerView.apply {
                        adapter = filmsAdapter.apply { submitList(films) }
                    }
                } else noFilmsTextView.show()
            }
        }
    }

    fun bindPlanet(planet: PlanetPresentation?) {
        planet?.let {
            with(binding.planetLayout) {
                planetProgressBar.remove()
                if (!planet.name.equals("")) {
                    this.planet = planet
                    detailsPlanetNameTextView.show()
                    detailsPlanetPopulationTextView.show()
                } else
                    noPlanetTextView.show()
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

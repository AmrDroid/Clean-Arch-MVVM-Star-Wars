package com.amrmustafa.casestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrmustafa.casestudy.domain.models.Film
import com.amrmustafa.casestudy.domain.models.Planet
import com.amrmustafa.casestudy.domain.models.Specie
import com.amrmustafa.casestudy.utils.ExceptionHandler
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.converters.toViewModel
import com.amrmustafa.casestudy.models.CharacterPresentation
import com.amrmustafa.casestudy.models.FavoritePresentation
import com.amrmustafa.casestudy.models.states.DetailsViewState
import com.amrmustafa.casestudy.models.states.ErrorState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

internal class DetailViewModel(
    private val getSpeciesUseCase: MainUseCase<String, Flow<List<Specie>>>,
    private val getPlanetUseCase: MainUseCase<String, Flow<Planet>>,
    private val getFilmsUseCase: MainUseCase<String, Flow<List<Film>>>
) : BaseViewModel() {



    private var characterDetailsJob: Job? = null

    val detailViewState: LiveData<DetailsViewState>
        get() = _detailViewState

    private var _detailViewState = MutableLiveData<DetailsViewState>()

    val remoteToFavoritePresentation: LiveData<FavoritePresentation>
        get() = _remoteToFavoritePresentation

    private var _remoteToFavoritePresentation = MutableLiveData<FavoritePresentation>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _detailViewState.value = _detailViewState.value?.copy(error = ErrorState(message))
    }

    init {
        _detailViewState.value =
            DetailsViewState(
                isComplete = false,
                error = null,
                planet = null,
                films = null,
                specie = null,
                info = null
            )
    }

    override fun onCleared() {
        super.onCleared()
        characterDetailsJob?.cancel()
    }

    fun initView(character: CharacterPresentation) {
        _detailViewState.value = _detailViewState.value?.copy(info = character)
    }

    fun getCharacterDetails(characterUrl: String, isRetry: Boolean = false) {
        if (isRetry) {
            _detailViewState.value = _detailViewState.value?.copy(error = null)
        }

        characterDetailsJob = launchCoroutine {
            async { loadFilms(characterUrl) }.await()
            async { loadSpecies(characterUrl) }.await()
            async { loadPlanet(characterUrl) }.await()
            _detailViewState.value = _detailViewState.value?.copy(isComplete = true)
        }
    }

    fun displaycharacterError(message: Int) {
        _detailViewState.value = _detailViewState.value?.copy(error = ErrorState(message))
    }

    fun createFavoritePresentationFromRemoteCharacter() {
        val character = _detailViewState.value?.info ?: return
        val planet = _detailViewState.value?.planet ?: return
        val films = _detailViewState.value?.films ?: return
        val species = _detailViewState.value?.specie ?: return
        val favoritePresentation = FavoritePresentation(
            characterPresentation = character,
            planetPresentation = planet,
            speciePresentation = species,
            films = films
        )
        _remoteToFavoritePresentation.value = favoritePresentation
    }

    private suspend fun loadPlanet(characterUrl: String) {
        getPlanetUseCase(characterUrl).collect { planet ->
            val planetPresentation = planet.toViewModel()
            _detailViewState.value = _detailViewState.value?.copy(planet = planetPresentation)
        }
    }

    private suspend fun loadFilms(characterUrl: String) {
        getFilmsUseCase(characterUrl).collect { films ->
            val filmsPresentation = films.map { film -> film.toViewModel() }
            _detailViewState.value = _detailViewState.value?.copy(films = filmsPresentation)
        }
    }

    private suspend fun loadSpecies(characterUrl: String) {
        getSpeciesUseCase(characterUrl).collect { species ->
            val speciesPresentation = species.map { specie -> specie.toViewModel()
            }
            _detailViewState.value = _detailViewState.value?.copy(specie = speciesPresentation)
        }
    }

}

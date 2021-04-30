package com.amrmustafa.casestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.utils.ExceptionHandler
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.converters.toViewModel
import com.amrmustafa.casestudy.models.FavoritePresentation
import com.amrmustafa.casestudy.models.states.FavoritesViewState
import com.amrmustafa.casestudy.models.states.ErrorState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

internal class FavoritesViewModel(
    private val getAllFavoritesUseCase: MainUseCase<Unit, Flow<List<Favorite>>>
    ) : BaseViewModel() {

    private var getAllFavoriteJob: Job? = null
    private var deleteAllFavoriteJob: Job? = null
    private var deleteFavoriteJob: Job? = null

    val favoritesViewState: LiveData<FavoritesViewState>
        get() = _favoriteViewState

    private var _favoriteViewState = MutableLiveData<FavoritesViewState>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _favoriteViewState.value =
            _favoriteViewState.value?.copy(isLoading = false, error = ErrorState(message))
    }

    init {
        _favoriteViewState.value =
            FavoritesViewState(isLoading = true, error = null, favorites = null)
        getAllFavorites()
    }

    override fun onCleared() {
        super.onCleared()
        getAllFavoriteJob?.cancel()
        deleteAllFavoriteJob?.cancel()
        deleteFavoriteJob?.cancel()
    }
    fun getAllFavorites() {
        getAllFavoriteJob = launchCoroutine {
            onFavsLoading()
            loadFavorites()
        }
    }

    private fun onFavsLoading() {
        _favoriteViewState.value = _favoriteViewState.value?.copy(isLoading = true)
    }

    private fun onFavsLoadingComplete(favs: List<FavoritePresentation>) {
        _favoriteViewState.value =
            _favoriteViewState.value?.copy(isLoading = false, favorites = favs)
    }

    private suspend fun loadFavorites() {
        getAllFavoritesUseCase(Unit).collect { favorites ->
            val favs = favorites.map { it.toViewModel() }
            onFavsLoadingComplete(favs)
        }
    }
}
package com.amrmustafa.casestudy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.utils.ExceptionHandler
import com.amrmustafa.casestudy.domain.models.Result
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.converters.toDomain
import com.amrmustafa.casestudy.models.FavoritePresentation
import com.amrmustafa.casestudy.models.states.ErrorState
import com.amrmustafa.casestudy.models.states.FavoriteViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

internal class FavoriteDetailViewModel(
    private val deleteFavoriteByNameUseCase: MainUseCase<String, Flow<Int>>,
    private val insertFavoriteUseCase: MainUseCase<Favorite, Flow<Result>>,
    private val getFavoriteByNameUseCase: MainUseCase<String, Flow<Favorite?>>
) : BaseViewModel() {

    private var saveFavoriteJob: Job? = null
    private var deleteFavoriteJob: Job? = null
    private var getFavoriteJob: Job? = null

    val favoriteViewState: LiveData<FavoriteViewState>
        get() = _favoriteViewState

    private var _favoriteViewState = MutableLiveData<FavoriteViewState>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _favoriteViewState.value = _favoriteViewState.value?.copy(error = ErrorState(message))
    }


    init {
        _favoriteViewState.value = FavoriteViewState(isFavorite = false, error = null)
    }

    override fun onCleared() {
        super.onCleared()
        saveFavoriteJob?.cancel()
        getFavoriteJob?.cancel()
        deleteFavoriteJob?.cancel()
    }

    fun saveFavorite(favoritePresentation: FavoritePresentation) {
        saveFavoriteJob = launchCoroutine {
            insertFavoriteUseCase(favoritePresentation.toDomain()).collect { result ->
                if (result == Result.SUCCESS) {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = true)
                } else {
                    Log.i(this.javaClass.simpleName, "Saving Favorite ErrorState!!")
                }
            }
        }
    }

    fun deleteFavorite(name: String) {
        deleteFavoriteJob = launchCoroutine {
            deleteFavoriteByNameUseCase(name).collect { row ->
                if (row == 1) {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = false)
                }
            }
        }
    }

    fun getFavorite(name: String) {
        getFavoriteJob = launchCoroutine {
            getFavoriteByNameUseCase(name).collect { fav ->
                fav?.run {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = true)
                }
            }
        }
    }
}

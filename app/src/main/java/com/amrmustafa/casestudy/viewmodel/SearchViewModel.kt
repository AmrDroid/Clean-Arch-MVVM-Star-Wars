package com.amrmustafa.casestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrmustafa.casestudy.utils.ExceptionHandler
import com.amrmustafa.casestudy.domain.models.Character
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.converters.toViewModel
import com.amrmustafa.casestudy.models.CharacterPresentation
import com.amrmustafa.casestudy.models.states.SearchViewState
import com.amrmustafa.casestudy.models.states.ErrorState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

internal class SearchViewModel(
    private val searchCharactersUseCase: MainUseCase<String, Flow<List<Character>>>
) : BaseViewModel() {


    private var searchJob: Job? = null

    val searchViewState: LiveData<SearchViewState>
        get() = _searchViewState

    private var _searchViewState = MutableLiveData<SearchViewState>()



    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        onSearchError(message)
    }


    init {
        _searchViewState.value =
            SearchViewState(
                isLoading = false,
                error = null,
                searchResults = null
            )
    }


    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }


    fun executeCharacterSearch(characterName: String) {
        searchJob?.cancel()
        searchJob = launchCoroutine {
            delay(300)
            onSearchLoading()

            searchCharactersUseCase(characterName).collect { results ->
                val characters = results.map { character -> character.toViewModel() }
                onSearchComplete(characters)
            }
        }
    }


    private fun onSearchComplete(characters: List<CharacterPresentation>) {
        _searchViewState.value =
            _searchViewState.value?.copy(isLoading = false, searchResults = characters)
    }

    private fun onSearchLoading() {
        _searchViewState.value = _searchViewState.value?.copy(isLoading = true)
    }

    private fun onSearchError(message: Int) {
        _searchViewState.value =
            _searchViewState.value?.copy(isLoading = false, error = ErrorState(message))
    }

}




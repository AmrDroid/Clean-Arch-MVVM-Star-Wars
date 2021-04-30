package com.amrmustafa.casestudy.models.states

import com.amrmustafa.casestudy.models.CharacterPresentation

internal data class SearchViewState(
    val isLoading: Boolean,
    val error: ErrorState?,
    val searchResults: List<CharacterPresentation>?
)
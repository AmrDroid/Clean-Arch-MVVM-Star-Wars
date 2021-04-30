package com.amrmustafa.casestudy.models.states

import com.amrmustafa.casestudy.models.FavoritePresentation

internal data class FavoritesViewState(
    val isLoading: Boolean,
    val error: ErrorState?,
    val favorites: List<FavoritePresentation>?
)
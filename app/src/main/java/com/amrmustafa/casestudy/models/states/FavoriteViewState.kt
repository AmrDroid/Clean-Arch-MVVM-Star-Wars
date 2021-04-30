package com.amrmustafa.casestudy.models.states


internal data class FavoriteViewState(
    val isFavorite: Boolean,
    val error: ErrorState?
)
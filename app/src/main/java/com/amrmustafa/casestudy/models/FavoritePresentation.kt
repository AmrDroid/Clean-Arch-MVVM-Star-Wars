package com.amrmustafa.casestudy.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class FavoritePresentation(
    val characterPresentation: CharacterPresentation,
    val planetPresentation: PlanetPresentation,
    val speciePresentation: List<SpeciePresentation>,
    val films: List<FilmPresentation>
) : Parcelable
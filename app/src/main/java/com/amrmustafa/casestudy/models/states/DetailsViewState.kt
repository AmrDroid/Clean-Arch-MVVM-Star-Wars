package com.amrmustafa.casestudy.models.states

import com.amrmustafa.casestudy.models.CharacterPresentation
import com.amrmustafa.casestudy.models.FilmPresentation
import com.amrmustafa.casestudy.models.PlanetPresentation
import com.amrmustafa.casestudy.models.SpeciePresentation

internal data class DetailsViewState(
    val isComplete: Boolean,
    val error: ErrorState?,
    val planet: PlanetPresentation?,
    val films: List<FilmPresentation>?,
    val specie: List<SpeciePresentation>?,
    val info: CharacterPresentation?
)
package com.amrmustafa.casestudy.converters

import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Film
import com.amrmustafa.casestudy.models.FavoritePresentation
import com.amrmustafa.casestudy.models.FilmPresentation

internal fun FavoritePresentation.toDomain(): Favorite {

    val population =
        if (planetPresentation.population == 0L) "Unknown" else planetPresentation.population.toString()
    return Favorite(
        characterPresentation.name,
        characterPresentation.birthYear,
        characterPresentation.heightInCm,
        planetPresentation.name,
        population,
        if (speciePresentation.isNotEmpty()) speciePresentation[0].name else "Unknown",
        if (speciePresentation.isNotEmpty()) speciePresentation[0].language else "Unknown",
        films.map { it.toDomain() })
}

internal fun FilmPresentation.toDomain(): Film {
    return Film(title, openingCrawl)
}
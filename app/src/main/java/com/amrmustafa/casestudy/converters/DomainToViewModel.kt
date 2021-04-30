package com.amrmustafa.casestudy.converters

import com.amrmustafa.casestudy.models.*
import com.amrmustafa.casestudy.domain.models.*
import com.amrmustafa.casestudy.utils.inchesConverter
import com.amrmustafa.casestudy.utils.populationToLong

//From Domain To Presentation Layer

internal fun Character.toViewModel(): CharacterPresentation {
    return CharacterPresentation(
        name,
        birthYear,
        height,
        inchesConverter(height),
        url
    )
}

internal fun Planet.toViewModel(): PlanetPresentation {
    return PlanetPresentation(name, populationToLong(population))
}

internal fun Film.toViewModel(): FilmPresentation {
    return FilmPresentation(title, openingCrawl)
}

internal fun Specie.toViewModel(): SpeciePresentation {
    return SpeciePresentation(name, language)
}

internal fun Favorite.toViewModel(): FavoritePresentation {

    val characterPresentation =
        CharacterPresentation(name, birthYear, height, inchesConverter(height), "")
    val planetPresentation = PlanetPresentation(planetName, populationToLong(planetPopulation))
    val speciePresentation = SpeciePresentation(specieName, specieLanguage)
    return FavoritePresentation(
        characterPresentation = characterPresentation,
        planetPresentation = planetPresentation,
        speciePresentation = listOf(speciePresentation),
        films = films.map { it.toViewModel() }
    )
}
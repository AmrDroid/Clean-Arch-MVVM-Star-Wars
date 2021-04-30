package com.amrmustafa.casestudy.data.local.converters

import com.amrmustafa.casestudy.data.local.models.FavEntity
import com.amrmustafa.casestudy.data.local.models.FavWithFilms
import com.amrmustafa.casestudy.data.local.models.FilmEntity
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Film


//From Domain Layer To Data Layer

internal fun Favorite.toEntity(): FavEntity {
    return FavEntity(
        name = name,
        birthYear = birthYear,
        height = height,
        planetName = planetName,
        planetPopulation = planetPopulation,
        specieLanguage = specieLanguage,
        specieName = specieName
    )

}
internal fun FavWithFilms.toDomain(): Favorite = Favorite(
        name = favEntity.name,
        birthYear = favEntity.birthYear,
        height = favEntity.height,
        planetName = favEntity.planetName,
        planetPopulation = favEntity.planetPopulation,
        specieLanguage = favEntity.specieLanguage,
        specieName = favEntity.specieName,
        films = filmEntities.map { it.toDomain() }
    )



internal fun Film.toEntity(favId: Long): FilmEntity = FilmEntity(title, openingCrawl, favId)

internal fun FilmEntity.toDomain(): Film = Film(title, openingCrawl)
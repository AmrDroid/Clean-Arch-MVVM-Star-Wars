package com.amrmustafa.casestudy.data.remote.converters
import com.amrmustafa.casestudy.data.remote.models.CharactersResponse
import com.amrmustafa.casestudy.data.remote.models.FilmDetailResponse
import com.amrmustafa.casestudy.data.remote.models.PlanetDetailsResponse
import com.amrmustafa.casestudy.data.remote.models.SpecieDetailResponse
import com.amrmustafa.casestudy.domain.models.*

//from remote data layer to provide data to domain layer

internal fun CharactersResponse.toDomain(): Character {
    return Character(name, birthYear, height, url)
}

internal fun PlanetDetailsResponse.toDomain(): Planet {
    return Planet(this.name, this.population)
}

internal fun SpecieDetailResponse.toDomain(): Specie {
    return Specie(this.name, this.language,this.homeworld)
}

internal fun FilmDetailResponse.toDomain(): Film {
    return Film(this.title, this.openingCrawl)
}
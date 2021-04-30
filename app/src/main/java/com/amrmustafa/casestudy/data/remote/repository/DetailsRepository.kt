package com.amrmustafa.casestudy.data.remote.repository

import com.amrmustafa.casestudy.data.remote.api.SwApiService
import com.amrmustafa.casestudy.data.remote.converters.toDomain
import com.amrmustafa.casestudy.domain.models.*
import com.amrmustafa.casestudy.domain.repository.IDetailsRepository
import com.amrmustafa.casestudy.utils.enforceHttps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


//Details Repository that interact with domain layer to provide data to presentation layer when required

class DetailsRepository(
    private val apiService: SwApiService
) : IDetailsRepository {

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> = flow {
        val speciesResponse = apiService.getSpecies(characterUrl.enforceHttps())
        val planetResponse=apiService.getPlanet(speciesResponse.specieUrls.component1().enforceHttps())
        val planet = apiService.getPlanetDetails(planetResponse.homeworldUrl.enforceHttps())
        emit(planet.toDomain())
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> = flow {
        val speciesResponse = apiService.getSpecies(characterUrl.enforceHttps())
        val species = mutableListOf<Specie>()
        for (specieUrl in speciesResponse.specieUrls) {
            val specie = apiService.getSpecieDetails(specieUrl.enforceHttps())
            species.add(specie.toDomain())
        }
        emit(species)
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> = flow {
        val filmsResponse = apiService.getFilms(characterUrl.enforceHttps())
        val films = mutableListOf<Film>()
        for (filmUrl in filmsResponse.filmUrls) {
            val film = apiService.getFilmDetails(filmUrl.enforceHttps())
            films.add(film.toDomain())
        }
        emit(films)
    }
}
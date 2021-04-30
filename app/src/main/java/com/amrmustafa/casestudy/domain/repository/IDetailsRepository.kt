package com.amrmustafa.casestudy.domain.repository


import com.amrmustafa.casestudy.domain.models.Film
import com.amrmustafa.casestudy.domain.models.Planet
import com.amrmustafa.casestudy.domain.models.Specie
import kotlinx.coroutines.flow.Flow


interface IDetailsRepository {

    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>

    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>>

    suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>>

}
package com.amrmustafa.casestudy.data.remote.repository

import com.amrmustafa.casestudy.data.remote.BaseClient
import com.amrmustafa.casestudy.domain.repository.IDetailsRepository
import com.amrmustafa.casestudy.data.remote.ApiConstants.EXISTING_CHARACTER_URL
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class DetailsRepositoryClient : BaseClient() {

    private lateinit var detailsRepository: IDetailsRepository

    @Before
    override fun setup() {
        super.setup()
        detailsRepository = DetailsRepository(swApiService)
    }

    @Test
    fun get_character_films_test() {
        runBlocking {
            val filmsFlow = detailsRepository.getCharacterFilms(EXISTING_CHARACTER_URL)

            filmsFlow.collect { films ->
                Truth.assertThat(films.size).isAtLeast(1)
            }

        }
    }

    @Test
    fun get_character_species_test() {
        runBlocking {
            val speciesFlow = detailsRepository.getCharacterSpecies(EXISTING_CHARACTER_URL)
            speciesFlow.collect { species ->
                Truth.assertThat(species.size).isAtLeast(1)
            }
        }
    }

    @Test
    fun get_character_planet_test() {
        runBlocking {
            val planetFlow = detailsRepository.getCharacterPlanet(EXISTING_CHARACTER_URL)
            planetFlow.collect { planet ->
                Truth.assertThat(planet.name).matches("Coruscant")
            }
        }
    }
}

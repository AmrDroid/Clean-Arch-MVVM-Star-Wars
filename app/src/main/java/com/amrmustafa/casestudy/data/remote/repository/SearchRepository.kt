package com.amrmustafa.casestudy.data.remote.repository

import com.amrmustafa.casestudy.data.remote.api.SwApiService
import com.amrmustafa.casestudy.domain.models.Character
import com.amrmustafa.casestudy.data.remote.converters.toDomain
import com.amrmustafa.casestudy.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//Search Repository that interact with domain layer to provide data to presentation layer when required


class SearchRepository(
    private val apiService: SwApiService
) : ISearchRepository {

    override suspend fun searchCharacters(characterName: String): Flow<List<Character>> = flow {
        val searchResponse = apiService.searchCharacters(characterName)
        val starWarsCharacters = mutableListOf<Character>()
        for (starWarsCharacter in searchResponse.results) {
            starWarsCharacters.add(starWarsCharacter.toDomain())
        }
        emit(starWarsCharacters)
    }

}
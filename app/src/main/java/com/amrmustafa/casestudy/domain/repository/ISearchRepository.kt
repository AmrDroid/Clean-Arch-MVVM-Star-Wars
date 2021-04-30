package com.amrmustafa.casestudy.domain.repository

import com.amrmustafa.casestudy.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    suspend fun searchCharacters(characterName: String): Flow<List<Character>>
}
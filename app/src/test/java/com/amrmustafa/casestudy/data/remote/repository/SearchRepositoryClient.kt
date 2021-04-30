package com.amrmustafa.casestudy.data.remote.repository

import com.amrmustafa.casestudy.data.remote.BaseClient
import com.amrmustafa.casestudy.data.remote.ApiConstants.EXISTING_SEARCH_PARAMS
import com.amrmustafa.casestudy.data.remote.ApiConstants.NON_EXISTENT_SEARCH_PARAMS
import com.amrmustafa.casestudy.domain.repository.ISearchRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class SearchRepositoryClient : BaseClient() {

    private lateinit var characterSearchRepository: ISearchRepository

    @Before
    override fun setup() {
        super.setup()
        characterSearchRepository = SearchRepository(swApiService)
    }

    @Test
    fun search_character_existing_result_test() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(EXISTING_SEARCH_PARAMS)
            results.collect {
                Truth.assertThat(it).isNotEmpty()
            }
        }
    }

    @Test
    fun search_characters_no_result_test() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(NON_EXISTENT_SEARCH_PARAMS)
            results.collect {
                Truth.assertThat(it).isEmpty()
            }
        }
    }

}
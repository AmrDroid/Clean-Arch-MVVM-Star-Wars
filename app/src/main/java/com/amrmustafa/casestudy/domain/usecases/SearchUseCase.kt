package com.amrmustafa.casestudy.domain.usecases

import com.amrmustafa.casestudy.domain.models.Character
import com.amrmustafa.casestudy.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(
    private val searchRepository: ISearchRepository
) : MainUseCase<String, Flow<List<Character>>> {

    override suspend operator fun invoke(params: String) = searchRepository.searchCharacters(params)

}
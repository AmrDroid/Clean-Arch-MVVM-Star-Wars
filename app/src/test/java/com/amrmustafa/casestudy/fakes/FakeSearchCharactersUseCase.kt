package com.amrmustafa.casestudy.fakes

import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.domain.models.*
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow


class FakeSearchCharactersUseCase(
    uiState: UiState
) : BaseTestUseCase<List<Character>, String>(uiState), MainUseCase<String, Flow<List<Character>>> {

    override suspend fun invoke(params: String): Flow<List<Character>> = execute(params)

    override fun getValue(params: String): List<Character> {
        return if (params.contentEquals("Amr")) Data.characters else emptyList()
    }

}
package com.amrmustafa.casestudy.fakes

import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Film
import com.amrmustafa.casestudy.domain.models.Planet
import com.amrmustafa.casestudy.domain.models.Specie
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Data.CHARACTER_URL
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow

class FakeGetSpeciesUseCase(
    uiState: UiState
) : BaseTestUseCase<List<Specie>, String>(uiState), MainUseCase<String, Flow<List<Specie>>> {

    override suspend fun invoke(params: String): Flow<List<Specie>> = execute(params)

    override fun getValue(params: String): List<Specie> {
        return if (params.contentEquals(CHARACTER_URL)) Data.species else emptyList()
    }

}
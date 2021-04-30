package com.amrmustafa.casestudy.fakes

import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Film
import com.amrmustafa.casestudy.domain.models.Planet
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Data.CHARACTER_URL
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow


class FakeGetPlanetUseCase(
    uiState: UiState
) : BaseTestUseCase<Planet, String>(uiState), MainUseCase<String, Flow<Planet>> {

    override suspend fun invoke(params: String): Flow<Planet> = execute(params)

    override fun getValue(params: String): Planet {
        return if (params.contentEquals(CHARACTER_URL)) Data.planet else throw IllegalStateException()
    }


}
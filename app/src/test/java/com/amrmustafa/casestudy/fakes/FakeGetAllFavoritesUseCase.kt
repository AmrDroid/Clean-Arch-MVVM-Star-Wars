package com.amrmustafa.casestudy.fakes

import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow


class FakeGetAllFavoritesUseCase(
    uiState: UiState
) : BaseTestUseCase<List<Favorite>, Unit>(uiState), MainUseCase<Unit, Flow<List<Favorite>>>  {

    override suspend fun invoke(params: Unit): Flow<List<Favorite>> = execute(params)

    override fun getValue(params: Unit): List<Favorite> = Data.favorites

}
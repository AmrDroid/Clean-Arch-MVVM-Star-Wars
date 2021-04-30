package com.amrmustafa.casestudy.fakes

import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow


class FakeGetFavoriteByNameUseCase(
    uiState: UiState
) : BaseTestUseCase<Favorite?, String>(uiState), MainUseCase<String, Flow<Favorite?>> {

    override suspend fun invoke(params: String) = execute(params)

    override fun getValue(params: String): Favorite? {
        return Data.favorites.filter { it.name == params }.firstOrNull()
    }


}
package com.amrmustafa.casestudy.fakes

import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow
import com.amrmustafa.casestudy.domain.models.Result

class FakeInsertFavoriteUseCase(
    uiState: UiState
) : BaseTestUseCase<Result, Favorite>(uiState), MainUseCase<Favorite, Flow<Result>> {

    override suspend fun invoke(params: Favorite): Flow<Result> {
        Data.favorites.add(params)
        return execute(params)
    }

    override fun getValue(params: Favorite): Result {
        return if (Data.favorites.size == 1) Result.SUCCESS else Result.FAILURE
    }

}


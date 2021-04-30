package com.amrmustafa.casestudy.fakes

import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Film
import com.amrmustafa.casestudy.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Data.CHARACTER_URL
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow



class FakeGetFilmsUseCase(
    uiState: UiState
) : BaseTestUseCase<List<Film>, String>(uiState), MainUseCase<String, Flow<List<Film>>>  {

    override suspend fun invoke(params: String): Flow<List<Film>> = execute(params)

    override fun getValue(params: String): List<Film> {
        return if (params.contentEquals(CHARACTER_URL)) Data.films else emptyList()
    }

}
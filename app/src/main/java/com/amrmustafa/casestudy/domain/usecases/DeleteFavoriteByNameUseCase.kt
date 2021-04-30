package com.amrmustafa.casestudy.domain.usecases

import com.amrmustafa.casestudy.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow

class DeleteFavoriteByNameUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<String, Flow<Int>> {

    override suspend fun invoke(params: String) = favRepository.deleteFavoriteByName(params)

}
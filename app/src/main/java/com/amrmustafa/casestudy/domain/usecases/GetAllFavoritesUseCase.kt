package com.amrmustafa.casestudy.domain.usecases

import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<Unit, Flow<List<Favorite>>> {

    override suspend fun invoke(params: Unit) = favRepository.getAllFavorites()

}
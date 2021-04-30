package com.amrmustafa.casestudy.domain.usecases

import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Result
import com.amrmustafa.casestudy.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow


class InsertFavoriteUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<Favorite, Flow<Result>> {

    override suspend fun invoke(params: Favorite) = favRepository.insertFavorite(params)

}
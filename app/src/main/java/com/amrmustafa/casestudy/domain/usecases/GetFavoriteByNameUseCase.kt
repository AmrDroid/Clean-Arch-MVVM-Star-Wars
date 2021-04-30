package com.amrmustafa.casestudy.domain.usecases
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow


class GetFavoriteByNameUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<String, Flow<Favorite?>> {

    override suspend fun invoke(params: String) = favRepository.getFavoriteByName(params)

}
package com.amrmustafa.casestudy.domain.usecases
import com.amrmustafa.casestudy.domain.models.Film
import com.amrmustafa.casestudy.domain.repository.IDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetFilmsUseCase(
    private val detailsRepository: IDetailsRepository
) : MainUseCase<String, Flow<List<Film>>> {

    override suspend operator fun invoke(params: String) =
        detailsRepository.getCharacterFilms(params)
}
package com.amrmustafa.casestudy.domain.usecases
import com.amrmustafa.casestudy.domain.models.Planet
import com.amrmustafa.casestudy.domain.repository.IDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetPlanetUseCase(
    private val detailsRepository: IDetailsRepository
) : MainUseCase<String, Flow<Planet>> {

    override suspend operator fun invoke(params: String) =
        detailsRepository.getCharacterPlanet(params)
}
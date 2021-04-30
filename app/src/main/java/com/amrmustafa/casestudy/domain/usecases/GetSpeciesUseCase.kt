package com.amrmustafa.casestudy.domain.usecases

import com.amrmustafa.casestudy.domain.models.Specie
import com.amrmustafa.casestudy.domain.repository.IDetailsRepository
import kotlinx.coroutines.flow.Flow


class GetSpeciesUseCase(
    private val detailsRepository: IDetailsRepository
) : MainUseCase<String, Flow<List<Specie>>> {

    override suspend operator fun invoke(params: String) =
        detailsRepository.getCharacterSpecies(params)

}
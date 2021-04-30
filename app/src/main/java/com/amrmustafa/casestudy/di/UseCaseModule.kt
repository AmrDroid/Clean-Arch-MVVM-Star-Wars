package com.amrmustafa.casestudy.di

import com.amrmustafa.casestudy.domain.models.*
import com.amrmustafa.casestudy.domain.repository.IDetailsRepository
import com.amrmustafa.casestudy.domain.repository.ISearchRepository
import com.amrmustafa.casestudy.domain.repository.IFavRepository
import com.amrmustafa.casestudy.domain.usecases.*
import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {

    single(named("search")) { provideSearchUseCase(get()) }

    single(named("species")) { provideSpeciesUseCase(get()) }

    single(named("planet")) { providePlanetUseCase(get()) }

    single(named("films")) { provideFilmsUseCase(get()) }

    single(named("delete_favorite_by_name")) { provideDeleteFavoriteByNameUseCase(get()) }

    single(named("get_favorite_by_name")) { provideGetFavoriteByNameUseCase(get()) }

    single(named("get_all_favorites")) { provideGetAllFavoritesUseCase(get()) }

    single(named("insert_favorite")) { provideInsertFavoriteUseCase(get()) }

}

fun provideSearchUseCase(searchRepository: ISearchRepository): MainUseCase<String, Flow<List<Character>>> {
    return SearchUseCase(searchRepository)
}

fun provideSpeciesUseCase(detailsRepository: IDetailsRepository): MainUseCase<String, Flow<List<Specie>>> {
    return GetSpeciesUseCase(detailsRepository)
}

fun providePlanetUseCase(detailsRepository: IDetailsRepository): MainUseCase<String, Flow<Planet>> {
    return GetPlanetUseCase(detailsRepository)
}

fun provideFilmsUseCase(detailsRepository: IDetailsRepository): MainUseCase<String, Flow<List<Film>>> {
    return GetFilmsUseCase(detailsRepository)
}


fun provideDeleteFavoriteByNameUseCase(favRepository: IFavRepository): MainUseCase<String, Flow<Int>> {
    return DeleteFavoriteByNameUseCase(favRepository)
}

fun provideGetAllFavoritesUseCase(favRepository: IFavRepository): MainUseCase<Unit, Flow<List<Favorite>>> {
    return GetAllFavoritesUseCase(favRepository)
}

fun provideGetFavoriteByNameUseCase(favRepository: IFavRepository): MainUseCase<String, Flow<Favorite?>> {
    return GetFavoriteByNameUseCase(favRepository)
}

fun provideInsertFavoriteUseCase(favRepository: IFavRepository): MainUseCase<Favorite, Flow<Result>> {
    return InsertFavoriteUseCase(favRepository)
}

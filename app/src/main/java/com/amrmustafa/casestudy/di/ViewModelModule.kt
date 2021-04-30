package com.amrmustafa.casestudy.di

import com.amrmustafa.casestudy.viewmodel.DetailViewModel
import com.amrmustafa.casestudy.viewmodel.FavoritesViewModel
import com.amrmustafa.casestudy.viewmodel.SearchViewModel
import com.amrmustafa.casestudy.viewmodel.FavoriteDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        SearchViewModel(
            searchCharactersUseCase  = get(named("search"))
        )
    }

    viewModel {
        DetailViewModel(
            getFilmsUseCase = get(named("films")),
            getPlanetUseCase = get(named("planet")),
            getSpeciesUseCase = get(named("species"))
        )
    }

    viewModel {
        FavoritesViewModel(
            getAllFavoritesUseCase = get(named("get_all_favorites"))
        )
    }

    viewModel {
        FavoriteDetailViewModel(
            deleteFavoriteByNameUseCase = get(named("delete_favorite_by_name")),
            insertFavoriteUseCase = get(named("insert_favorite")),
            getFavoriteByNameUseCase = get(named("get_favorite_by_name"))
        )
    }

}
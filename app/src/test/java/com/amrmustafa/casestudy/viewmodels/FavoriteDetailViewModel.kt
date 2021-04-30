package com.amrmustafa.casestudy.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amrmustafa.casestudy.converters.toViewModel
import com.amrmustafa.casestudy.fakes.FakeDeleteFavoriteByNameUseCase
import com.amrmustafa.casestudy.fakes.FakeGetFavoriteByNameUseCase
import com.amrmustafa.casestudy.fakes.FakeInsertFavoriteUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.UiState
import com.amrmustafa.casestudy.utils.observeOnce
import com.amrmustafa.casestudy.viewmodel.FavoriteDetailViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
internal class FavoriteDetailViewModel : BaseViewModelTest() {

    private lateinit var favoriteDetailViewModel: FavoriteDetailViewModel

    override fun prepareViewModel(uiState: UiState) {
        val deleteFavoriteByNameUseCase = FakeDeleteFavoriteByNameUseCase(uiState)
        val insertFavoriteUseCase = FakeInsertFavoriteUseCase(uiState)
        val getFavoriteByNameUseCase = FakeGetFavoriteByNameUseCase(uiState)
        favoriteDetailViewModel = FavoriteDetailViewModel(
            deleteFavoriteByNameUseCase,
            insertFavoriteUseCase,
            getFavoriteByNameUseCase
        )
    }


    @Test
    fun add_remove_character_details_from_favorites_test() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.SUCCESS)

            favoriteDetailViewModel.saveFavorite(Data.favorite.toViewModel())

            favoriteDetailViewModel.favoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isTrue()
                Truth.assertThat(Data.favorites.size).isEqualTo(1)
            }

            favoriteDetailViewModel.deleteFavorite(Data.favorite.name)

            favoriteDetailViewModel.favoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isFalse()
                Truth.assertThat(Data.favorites.size).isEqualTo(0)
            }
        }
    }


    @Test
    fun given_character_name_check_if_favorite() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.SUCCESS)

            favoriteDetailViewModel.saveFavorite(Data.favorite.toViewModel())

            favoriteDetailViewModel.getFavorite(Data.favorite.name)

            favoriteDetailViewModel.favoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isTrue()
            }
        }
    }


    @Test
    fun delete_specific_favorite() {
        coroutineTestRule.dispatcher.runBlockingTest {
            Data.favorites.add(Data.favorite)

            prepareViewModel(UiState.SUCCESS)

            favoriteDetailViewModel.deleteFavorite(Data.favorite.name)

            favoriteDetailViewModel.favoriteViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.isFavorite).isFalse()
            }
        }
    }

    @After
    fun clear() {
        Data.favorites.clear()
    }



}

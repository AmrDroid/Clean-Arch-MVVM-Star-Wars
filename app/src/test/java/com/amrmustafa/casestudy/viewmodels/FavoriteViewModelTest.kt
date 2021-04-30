package com.amrmustafa.casestudy.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.amrmustafa.casestudy.fakes.FakeGetAllFavoritesUseCase
import com.amrmustafa.casestudy.utils.UiState
import com.amrmustafa.casestudy.utils.observeOnce
import com.amrmustafa.casestudy.viewmodel.FavoritesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
internal class FavoriteViewModelTest : BaseViewModelTest() {

    private lateinit var dashboardFavoritesViewModel: FavoritesViewModel

    override fun prepareViewModel(uiState: UiState) {
        val getAllFavoritesUseCase = FakeGetAllFavoritesUseCase(uiState)

        dashboardFavoritesViewModel = FavoritesViewModel(
            getAllFavoritesUseCase
        )

    }


    @Test
    fun get_all_favorites() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)

            dashboardFavoritesViewModel.getAllFavorites()

            dashboardFavoritesViewModel.favoritesViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.isLoading).isFalse()
                Truth.assertThat(state.favorites).isNotNull()
            }
        }
    }


}
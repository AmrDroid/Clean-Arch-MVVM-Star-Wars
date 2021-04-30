package com.amrmustafa.casestudy.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amrmustafa.casestudy.fakes.FakeGetFilmsUseCase
import com.amrmustafa.casestudy.fakes.FakeGetPlanetUseCase
import com.amrmustafa.casestudy.fakes.FakeGetSpeciesUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.UiState
import com.amrmustafa.casestudy.utils.observeOnce
import com.amrmustafa.casestudy.viewmodel.DetailViewModel
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
internal class DetailViewModelTest : BaseViewModelTest() {

    private lateinit var detailViewModel: DetailViewModel


    override fun prepareViewModel(uiState: UiState) {
        val getFilmsUseCase = FakeGetFilmsUseCase(uiState)
        val getPlanetUseCase = FakeGetPlanetUseCase(uiState)
        val getSpeciesUseCase = FakeGetSpeciesUseCase(uiState)

        detailViewModel =
            DetailViewModel(
                getSpeciesUseCase,
                getPlanetUseCase,
                getFilmsUseCase
            )
    }


    @Test
    fun get_character_details_from_url() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)
            detailViewModel.getCharacterDetails(Data.CHARACTER_URL)

            detailViewModel.detailViewState.observeOnce { detailViewState ->
                Truth.assertThat(detailViewState.error).isNull()
                Truth.assertThat(detailViewState.films).isNotEmpty()
                Truth.assertThat(detailViewState.planet).isNotNull()
                Truth.assertThat(detailViewState.specie).isNotEmpty()
            }
        }
    }


    @Test
    fun get_character_details_from_invalid_url() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.ERROR)
            detailViewModel.getCharacterDetails(Data.CHARACTER_URL)
            detailViewModel.detailViewState.observeOnce { detailViewState ->
                Truth.assertThat(detailViewState.error).isNotNull()
            }
        }
    }

    @After
    fun clear() {
        Data.favorites.clear()
    }
}

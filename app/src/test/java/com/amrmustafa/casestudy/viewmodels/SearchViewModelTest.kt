package com.amrmustafa.casestudy.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amrmustafa.casestudy.fakes.FakeSearchCharactersUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.UiState
import com.amrmustafa.casestudy.utils.observeOnce
import com.amrmustafa.casestudy.viewmodel.SearchViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
class SearchViewModelTest : BaseViewModelTest() {

    private lateinit var dashboardSearchViewModel: SearchViewModel


    override fun prepareViewModel(uiState: UiState) {
        val searchCharactersUseCase = FakeSearchCharactersUseCase(uiState)
        dashboardSearchViewModel = SearchViewModel(searchCharactersUseCase)
    }


    @Test
    fun search_result_success_state() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)
            dashboardSearchViewModel.executeCharacterSearch(Data.SEARCH_PARAM)

            advanceTimeBy(500)

            dashboardSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.searchResults).isNotEmpty()
            }
        }
    }
    @Test
    fun invalid_search_query_error_state() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.ERROR)
            dashboardSearchViewModel.executeCharacterSearch("eed")

            advanceTimeBy(600)

            dashboardSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNotNull()
            }

        }
    }
    @Test
    fun empty_search_query_error_state() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.ERROR)
            dashboardSearchViewModel.executeCharacterSearch("")

            advanceTimeBy(600)

            dashboardSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNotNull()
            }

        }
    }


}
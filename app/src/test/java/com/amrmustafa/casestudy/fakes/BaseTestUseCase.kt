package com.amrmustafa.casestudy.data.fakes
import com.amrmustafa.casestudy.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


abstract class BaseTestUseCase<out T, in P>(private val uiState: UiState) {

    fun execute(params: P): Flow<T> = flow {
        when (uiState) {
            UiState.SUCCESS -> emit(getValue(params))
            UiState.ERROR -> throw Exception("Error !!")
        }
    }

    abstract fun getValue(params: P): T

}
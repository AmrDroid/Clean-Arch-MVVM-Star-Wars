package com.amrmustafa.casestudy.domain.repository


import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface IFavRepository {

    fun getAllFavorites(): Flow<List<Favorite>>

    fun getFavoriteByName(name: String): Flow<Favorite?>

    fun deleteFavoriteByName(name: String): Flow<Int>

    fun insertFavorite(favorite: Favorite): Flow<Result>

}
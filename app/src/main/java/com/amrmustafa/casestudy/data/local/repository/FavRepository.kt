package com.amrmustafa.casestudy.data.local.repository

import com.amrmustafa.casestudy.data.local.dao.FavDao
import com.amrmustafa.casestudy.data.local.converters.toDomain
import com.amrmustafa.casestudy.domain.models.Result
import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//local Favorites Repository that interact with domain layer to provide data to presentation layer when required

class FavRepository(private val favoritesDao: FavDao) : IFavRepository {

    override fun getAllFavorites(): Flow<List<Favorite>> = flow {
        val favs = favoritesDao.getAllFav()
        emit(favs.map { it.toDomain() })
    }
    override fun getFavoriteByName(name: String): Flow<Favorite?> = flow {
        val fav = favoritesDao.getFavByName(name)
        if (fav != null)
            emit(fav.toDomain())
        else
            emit(null)

    }
    override fun deleteFavoriteByName(name: String): Flow<Int> = flow {
        val rowsAffected = favoritesDao.deleteFavByName(name)
        emit(rowsAffected)
    }
    override fun insertFavorite(favorite: Favorite): Flow<Result> = flow {
        val result = favoritesDao.insert(favorite)
        emit(result)
    }

}
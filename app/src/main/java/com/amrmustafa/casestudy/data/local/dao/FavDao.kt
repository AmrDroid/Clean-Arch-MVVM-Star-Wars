package com.amrmustafa.casestudy.data.local.dao
import androidx.room.*
import com.amrmustafa.casestudy.data.local.converters.toEntity
import com.amrmustafa.casestudy.domain.models.Result
import com.amrmustafa.casestudy.data.local.models.FavEntity
import com.amrmustafa.casestudy.data.local.models.FavWithFilms
import com.amrmustafa.casestudy.data.local.models.FilmEntity
import com.amrmustafa.casestudy.domain.models.Favorite
//Local Database Operations
@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(favEntity: FavEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmEntity: FilmEntity): Long

    @Transaction
    suspend fun insert(favorite: Favorite):Result {
        val favId = insertFav(favorite.toEntity())
        for (film in favorite.films) {
            val filmEntity = film.toEntity(favId)
            insertFilm(filmEntity)
        }
        return Result.SUCCESS
    }

    @Query("DELETE FROM favorites WHERE name=:name")
    suspend fun deleteFavByName(name: String): Int

    @Transaction
    @Query("SELECT * FROM favorites WHERE name=:name")
    suspend fun getFavByName(name: String): FavWithFilms

    @Transaction
    @Query("SELECT * FROM favorites")
    suspend fun getAllFav(): List<FavWithFilms>

}
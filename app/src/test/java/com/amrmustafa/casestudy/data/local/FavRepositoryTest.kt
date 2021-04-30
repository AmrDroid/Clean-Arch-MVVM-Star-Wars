package com.amrmustafa.casestudy.data.local

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.amrmustafa.casestudy.data.local.dao.FavDao
import com.amrmustafa.casestudy.data.local.repository.FavRepository
import com.amrmustafa.casestudy.domain.repository.IFavRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class FavRepositoryTest : AutoCloseKoinTest() {

    private lateinit var favRepository: IFavRepository
    private lateinit var db: MainDatabase
    protected lateinit var favDao: FavDao

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java).build()
        favDao = db.favoritesDao()
        favRepository = FavRepository(favoritesDao = favDao)
    }

    @Test
    fun save_favorites_get_all_favorites_test() {
        runBlocking(Dispatchers.IO) {

            favRepository.insertFavorite(Data.favorite).collect()

            favRepository.insertFavorite(Data.favorite2).collect()

            val favorites = favRepository.getAllFavorites()

            favorites.collect { favs ->
                Truth.assertThat(favs).isEqualTo(listOf(Data.favorite,Data.favorite2))
            }
        }
    }

    @Test
    fun get_specific_favorite_test() {
        runBlocking(Dispatchers.IO) {

            favRepository.insertFavorite(Data.favorite2).collect()

            val favorite = favRepository.getFavoriteByName(Data.favorite2.name)

            favorite.collect { favs ->
                Truth.assertThat(favs).isEqualTo(Data.favorite2)
            }
        }
    }
    @Test
    fun delete_specific_favoriteTest() {
        runBlocking(Dispatchers.IO) {

            favRepository.insertFavorite(Data.favorite).collect()

            val favorite = favRepository.deleteFavoriteByName(Data.favorite.name)

            favorite.collect { rowsAffected ->
                Truth.assertThat(rowsAffected).isEqualTo(1)
            }

        }
    }
    @After
    @Throws(IOException::class)
    fun deleteDataBase() {
        runBlocking(Dispatchers.IO) {
            db.clearAllTables()
        }
        db.close()
    }

}

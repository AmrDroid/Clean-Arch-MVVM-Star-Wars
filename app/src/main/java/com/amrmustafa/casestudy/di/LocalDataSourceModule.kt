package com.amrmustafa.casestudy.di

import androidx.room.Room
import com.amrmustafa.casestudy.data.local.MainDatabase
import com.amrmustafa.casestudy.data.local.dao.FavDao
import com.amrmustafa.casestudy.data.local.repository.FavRepository
import com.amrmustafa.casestudy.domain.repository.IFavRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val localDataSourceModule = module {

    single<IFavRepository> { FavRepository(favoritesDao = get()) }

    single {
        Room.databaseBuilder(androidContext(), MainDatabase::class.java, "starwars_db")
            .build()
    }

    single { provideFavoritesDao(db = get()) }


}

internal fun provideFavoritesDao(db: MainDatabase): FavDao = db.favoritesDao()
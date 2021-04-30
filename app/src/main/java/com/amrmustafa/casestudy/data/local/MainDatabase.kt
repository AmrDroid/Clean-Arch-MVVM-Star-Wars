package com.amrmustafa.casestudy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amrmustafa.casestudy.data.local.dao.FavDao
import com.amrmustafa.casestudy.data.local.models.FavEntity
import com.amrmustafa.casestudy.data.local.models.FilmEntity


@Database(entities = [FavEntity::class, FilmEntity::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavDao
}
package com.amrmustafa.casestudy.data.local.models
import androidx.room.Embedded
import androidx.room.Relation


data class FavWithFilms(
    @Embedded
    val favEntity: FavEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "fav_id"
    )
    val filmEntities: List<FilmEntity>
)
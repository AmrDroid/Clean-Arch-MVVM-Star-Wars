package com.amrmustafa.casestudy.data.remote.models
import com.squareup.moshi.Json


data class CharactersResponse(
    val name: String,
    @field:Json(name = "birth_year") val birthYear: String,
    val height: String,
    val url: String
)
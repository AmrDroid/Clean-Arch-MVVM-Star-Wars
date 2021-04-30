package com.amrmustafa.casestudy.utils

import com.amrmustafa.casestudy.domain.models.*


object Data {
    val favorite = Favorite(
        "angle",
        "12 BBY",
        "123",
        "planet",
        "100000",
        "specie",
        "language",
        listOf(Film("title", "crawl"))
    )
    val favorites = mutableListOf<Favorite>()
    val films = listOf(Film(title = "title", openingCrawl = "opening crawl"))
    val planet = Planet(name = "name", population = "100000")
    val species = listOf(Specie(name = "name", language = "language",""))
    val characters = listOf(
        Character(
            "Amr Elsayed",
            "12BBY",
            "123",
            "/api/species/2/"
        )
    )

    const val CHARACTER_URL = "/api/people/1/"
    const val SEARCH_PARAM = "Amr"

}
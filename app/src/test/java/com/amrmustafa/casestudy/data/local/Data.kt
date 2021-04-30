package com.amrmustafa.casestudy.data.local

import com.amrmustafa.casestudy.domain.models.Favorite
import com.amrmustafa.casestudy.domain.models.Film


internal object Data {
    val favorite = Favorite(
        name = "Amr",
        birthYear = "20 BBY",
        height = "195",
        planetName = "Teto",
        planetPopulation = "100",
        specieName = "Test",
        specieLanguage = "English",
        films = listOf(Film("title", "craw"))
    )
    val favorite2 = Favorite(
        name = "Angle",
        birthYear = "18 BBY",
        height = "170",
        planetName = "test",
        planetPopulation = "100",
        specieName = "Test",
        specieLanguage = "German",
        films = listOf(Film("title", "craw"))
    )
}
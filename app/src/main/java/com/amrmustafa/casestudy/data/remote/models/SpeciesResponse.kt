package com.amrmustafa.casestudy.data.remote.models

import com.squareup.moshi.Json

data class SpeciesResponse(@field:Json(name = "species") val specieUrls: List<String>)


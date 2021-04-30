package com.amrmustafa.casestudy.data.remote.models

import com.squareup.moshi.Json

data class PlanetResponse(@field:Json(name = "homeworld") val homeworldUrl: String)


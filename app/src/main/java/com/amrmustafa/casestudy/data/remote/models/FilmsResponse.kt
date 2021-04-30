package com.amrmustafa.casestudy.data.remote.models

import com.squareup.moshi.Json

data class FilmsResponse(@field:Json(name = "films")val filmUrls: List<String>)


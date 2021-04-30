package com.amrmustafa.casestudy.data.remote.models


data class SearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CharactersResponse>
)


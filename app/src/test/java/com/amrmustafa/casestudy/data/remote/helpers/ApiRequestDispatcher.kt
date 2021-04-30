package com.amrmustafa.casestudy.data.remote.helpers

import com.amrmustafa.casestudy.data.remote.ApiConstants.EXISTING_SEARCH_PARAMS
import com.amrmustafa.casestudy.data.remote.ApiConstants.FILM_URL
import com.amrmustafa.casestudy.data.remote.ApiConstants.NON_EXISTENT_SEARCH_PARAMS
import com.amrmustafa.casestudy.data.remote.ApiConstants.PLANET_URL
import com.amrmustafa.casestudy.data.remote.ApiConstants.SPECIES_URL
import com.amrmustafa.casestudy.data.remote.resources.*
import com.amrmustafa.casestudy.data.remote.ApiConstants.EXISTING_CHARACTER_URL
import com.amrmustafa.casestudy.data.remote.ApiConstants.NON_EXISTANT_CHARACTER_URL
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection


internal class ApiRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/people/?search=$EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(SEARCH_RESULT)
            }
            "/people/?search=$NON_EXISTENT_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        NO_SEARCH_RESULT
                    )
            }
            NON_EXISTANT_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(NOT_FOUND)
            }
            EXISTING_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(CHARACTER_DETAILS)
            }
            SPECIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(SPECIES)
            }
            FILM_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(CHARACTER_FILMS)
            }
            PLANET_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(PLANET)
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}
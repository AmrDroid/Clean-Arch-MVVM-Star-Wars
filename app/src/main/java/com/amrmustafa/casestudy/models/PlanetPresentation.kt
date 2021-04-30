package com.amrmustafa.casestudy.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class PlanetPresentation(val name: String, val population: Long) : Parcelable
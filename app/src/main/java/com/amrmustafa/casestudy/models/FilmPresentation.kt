package com.amrmustafa.casestudy.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class FilmPresentation(val title: String, val openingCrawl: String) : Parcelable

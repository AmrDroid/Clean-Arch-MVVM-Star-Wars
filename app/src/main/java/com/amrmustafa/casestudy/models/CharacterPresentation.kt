package com.amrmustafa.casestudy.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
internal data class CharacterPresentation(
    val name: String,
    val birthYear: String,
    val heightInCm: String,
    val heightInInches: String,
    val url: String
) : Parcelable
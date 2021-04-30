package com.amrmustafa.casestudy.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
internal data class SpeciePresentation(val name: String, val language: String) : Parcelable

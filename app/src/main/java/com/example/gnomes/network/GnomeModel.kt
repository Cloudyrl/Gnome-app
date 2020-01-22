package com.example.gnomes.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GnomeModel(
    val id: Int,
    val name: String,
    @Json(name = "thumbnail")
    val thumbnailUrl: String,
    val age: Int,
    val weight: Double,
    val Height: Double,
    @Json(name = "hair_color")
    val hairColor: String,
    val professions: List<String>
): Parcelable
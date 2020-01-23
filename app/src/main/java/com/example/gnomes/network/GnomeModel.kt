package com.example.gnomes.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GnomeModel(
    val id: Int,
    val name: String,
    @Json(name = "thumbnail")
    val imageUrl: String,
    val age: Int,
    val weight: Double,
    val height: Double,
    @Json(name = "hair_color")
    val hairColor: String,
    val professions: List<String>,
    val friends: List<String>
) : Parcelable
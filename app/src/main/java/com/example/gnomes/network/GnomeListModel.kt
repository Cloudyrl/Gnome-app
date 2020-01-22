package com.example.gnomes.network

import com.squareup.moshi.Json

data class GnomeListModel(
    @Json(name = "Brastlewark")
    val gnomeList : List<GnomeModel>
)
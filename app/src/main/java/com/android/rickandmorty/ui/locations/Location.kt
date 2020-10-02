package com.android.rickandmorty.ui.locations

import java.util.*

data class Location (
    val id: Number,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: Date
)




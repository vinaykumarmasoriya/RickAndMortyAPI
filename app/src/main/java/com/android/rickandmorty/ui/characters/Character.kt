package com.android.rickandmorty.ui.characters

import java.util.Date

data class Character(
    val id: Number,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationLink,
    val location: LocationLink,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date
)

data class LocationLink(
    val name: String,
    val url: String
)

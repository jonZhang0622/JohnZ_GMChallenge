package com.example.johnz_gmchallenge.model


import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Result(
    val artistName: String? = "No artist name",
    val primaryGenreName: String? = "No Genre",
    val releaseDate: Date?,
    val trackName: String? = "Track name unknown",
    val trackPrice: Double?,
)
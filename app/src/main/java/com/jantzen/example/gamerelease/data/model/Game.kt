package com.jantzen.example.gamerelease.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game (
    val id: Int?,
    val name: String?,
    val platforms: List<Platform>?,
    val image: Image?,
    val desk: String?,
    val expected_release_year: Int?):Parcelable

package com.jantzen.example.gamerelease.data.model



data class Game (
    val name: String?,
    val platforms: List<Platform>?,
    val image: Image?,
    val deck: String?,
    val expected_release_year: Int?)

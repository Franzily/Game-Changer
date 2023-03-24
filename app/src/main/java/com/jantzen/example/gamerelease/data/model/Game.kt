package com.jantzen.example.gamerelease.data.model

data class Game (val cover: Int,
                 val name: String,
                 val releaseDate: String,
                 val description: String,
                 val gameModes: String,
                 val genres: String,
                 val plattforms: String,
                 val categorie: String,
                 val publisher: String
                 )
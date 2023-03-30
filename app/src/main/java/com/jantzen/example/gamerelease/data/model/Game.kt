package com.jantzen.example.gamerelease.data.model

data class Game (val cover: Int,
                 val name: String,
                 val date: String,
                 val description: String,
                 val gameModes: String,
                 val genres: String,
                 val platform: String,
                 val category: String,
                 val publisher: String,
                 val fav: Boolean
                 )
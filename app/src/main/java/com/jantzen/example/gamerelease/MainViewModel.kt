package com.jantzen.example.gamerelease

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jantzen.example.gamerelease.data.model.Game

class MainViewModel: ViewModel() {


    private val _games : MutableLiveData<List<Game>>()
    val games : LiveData<Game>
    get() = _games

    private val _favoriteGameList = MutableLiveData<List<Game>>(emptyList())
    val favoriteGameList: LiveData<List<Game>>
    get() = _favoriteGameList

    fun addGame(game: Game){
        val list = _games.value?.plus(game)
    }

    fun addGameToFav(game:Game){
        val currentList = _favoriteGameList.value?: emptyList()
        val newList = currentList + game
        _favoriteGameList.value = newList
    }
}
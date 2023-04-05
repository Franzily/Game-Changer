package com.jantzen.example.gamerelease

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantzen.example.gamerelease.data.Repository
import com.jantzen.example.gamerelease.data.model.*
import com.jantzen.example.gamerelease.data.remote.Game_ReleaseAPI
import com.jantzen.example.gamerelease.data.remote.TokenAPI
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var repo = Repository(TokenAPI, Game_ReleaseAPI)


    private val _token = MutableLiveData<ResponseToken>()
    val token: LiveData<ResponseToken>
        get() = _token

    val releaseDates: LiveData<List<Game_Release>>
        get() = repo.releaseDate

    val alternativeGame: LiveData<List<Game_Alternative>>
        get() = repo.alternativeGame

    val games: LiveData<List<Game>>
        get() = repo.games

    val cover: LiveData<List<Game_Cover>>
        get() = repo.cover


    init {
        Log.d(TAG, "init")
        viewModelScope.launch {
            _token.value = repo.getToken()
        }
    }

    fun loadReleaseData(token: String) {
        viewModelScope.launch {
            repo.loadReleaseDate(token)
        }
    }

    fun loadAlternativeData(token: String) {
        viewModelScope.launch {
            repo.loadAlternativeName(token)
        }
    }

    fun loadGame(token: String) {
        viewModelScope.launch {
            repo.loadGames(token)
        }
    }

     fun loadCover(token: String) {
         var gameID : MutableList<Int>
         games.value!![0].id
         gameID = mutableListOf()

         for (game in games.value!!){
             gameID.add(game.id!!)
         }

         viewModelScope.launch {
             repo.loadCover(token, gameID)

         }




     }
    fun addGameToFav(game: Game) {
        val currentList = _favoriteGameList.value ?: emptyList()
        val newList = currentList + game
        _favoriteGameList.value = newList
    }

    private val _games = MutableLiveData<List<Game>>()
    val games1: LiveData<List<Game>>
        get() = _games

    private val _favoriteGameList = MutableLiveData<List<Game>>(emptyList())
    val favoriteGameList: LiveData<List<Game>>
        get() = _favoriteGameList

    fun addGame(game: Game) {
        val list = _games.value?.plus(game)
    }
}

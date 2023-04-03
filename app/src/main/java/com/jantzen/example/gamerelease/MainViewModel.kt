package com.jantzen.example.gamerelease

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantzen.example.gamerelease.data.Repository
import com.jantzen.example.gamerelease.data.Repository_Release
import com.jantzen.example.gamerelease.data.model.Game
import com.jantzen.example.gamerelease.data.model.Game_Release
import com.jantzen.example.gamerelease.data.model.ResponseToken
import com.jantzen.example.gamerelease.data.remote.Game_ReleaseAPI
import com.jantzen.example.gamerelease.data.remote.TokenAPI
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var repo = Repository(TokenAPI)
    var repoRelease = Repository_Release(Game_ReleaseAPI)

    private val _token = MutableLiveData<ResponseToken>()
    val token : LiveData<ResponseToken>
    get() = _token


    lateinit var release: String





    init {
        Log.d(TAG, "init")
        viewModelScope.launch {
          _token.value = repo.getToken()
        }
        viewModelScope.launch {
            repoRelease.loadReleaseDate()
        }
        release = repoRelease.releaseDate.value.toString()
        Log.d(TAG,"token: ${_token.value.toString()}")
        Log.d(TAG, "release: ${release}")
    }



    private val _games = MutableLiveData<List<Game>>()
    val games : LiveData<List<Game>>
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
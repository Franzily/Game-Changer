package com.jantzen.example.gamerelease.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jantzen.example.gamerelease.data.model.*
import com.jantzen.example.gamerelease.data.remote.GameAPI
import com.jantzen.example.gamerelease.data.remote.TokenAPI

class Repository (private val tApi: TokenAPI, private val gameApi: GameAPI){


    private val _releaseDate = MutableLiveData<List<Game_Release>>()
    val releaseDate: LiveData<List<Game_Release>>
        get() = _releaseDate

    private val _alternativeGame = MutableLiveData<List<Game_Alternative>>()
    val alternativeGame: LiveData<List<Game_Alternative>>
    get() = _alternativeGame

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
    get() = _games

    private val _cover = MutableLiveData<List<Game_Cover>>()
    val cover : LiveData<List<Game_Cover>>
    get() = _cover

    suspend fun loadReleaseDate(token: String) {
        _releaseDate.value = gameApi.retrofitService.getRelease(auth = token)
    }

    suspend fun loadAlternativeName(token:String){
        _alternativeGame.value = gameApi.retrofitService.getName(auth = token)
    }

    suspend fun loadGames(token: String){
        _games.value = gameApi.retrofitService.getGame(auth = token)
    }

    suspend fun loadCover(token:String, gameID: List<Int>){
        var  ids: String =""
        if(gameID.size >=1){
        for (i in 0 until gameID.size){
            ids += (",${i.toString()}")
        }
        _cover.value = gameApi.retrofitService.getCover(auth = token, fields = "fields date,id,url; where id = (${ids})")


    }



    suspend fun getToken():ResponseToken{
        Log.d(TAG, "Api anfrage")
        return tApi.retrofitService.getToken()
       // Log.d(TAG, "${token.access_token}")

    }



}
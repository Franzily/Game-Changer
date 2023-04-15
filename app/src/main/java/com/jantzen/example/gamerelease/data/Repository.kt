package com.jantzen.example.gamerelease.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jantzen.example.gamerelease.data.model.*
import com.jantzen.example.gamerelease.data.remote.GameAPI

class Repository (private val gameApi: GameAPI) {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games

    suspend fun getGames(filter: String){
       try {

           val response = gameApi.retrofitService.getGame()
           _games.value = response.results!!
       } catch (e: Exception){
           Log.e("repository getgames", "error api ${e}")
       }


    }
}
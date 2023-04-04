package com.jantzen.example.gamerelease.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jantzen.example.gamerelease.data.model.Game_Release
import com.jantzen.example.gamerelease.data.model.ResponseToken
import com.jantzen.example.gamerelease.data.remote.Game_ReleaseAPI
import com.jantzen.example.gamerelease.data.remote.TokenAPI

class Repository (private val tApi: TokenAPI, private val gameApi: Game_ReleaseAPI){


    private val _releaseDate = MutableLiveData<List<Game_Release>>()
    val releaseDate: LiveData<List<Game_Release>>
        get() = _releaseDate

    suspend fun loadReleaseDate(token: String) {
        _releaseDate.value = gameApi.retrofitService.getRelease(auth = token)
    }
    suspend fun getToken():ResponseToken{
        Log.d(TAG, "Api anfrage")
        return tApi.retrofitService.getToken()
       // Log.d(TAG, "${token.access_token}")

    }
}
package com.jantzen.example.gamerelease.data

import android.content.ContentValues.TAG
import android.util.Log
import com.jantzen.example.gamerelease.data.model.ResponseToken
import com.jantzen.example.gamerelease.data.remote.TokenAPI

class Repository (private val tApi: TokenAPI){



    suspend fun getToken():ResponseToken{
        Log.d(TAG, "Api anfrage")
        return tApi.retrofitService.getToken()
       // Log.d(TAG, "${token.access_token}")

    }
}
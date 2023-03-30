package com.jantzen.example.gamerelease.data

import android.content.ContentValues.TAG
import android.util.Log
import com.jantzen.example.gamerelease.data.model.ResponseToken
import com.jantzen.example.gamerelease.data.remote.TokenAPI

class Repository (private val tApi: TokenAPI){

    lateinit var token: ResponseToken

    suspend fun getToken(){
        Log.d(TAG, "Api anfrage")
        token = tApi.retrofitService.getToken()
        Log.d(TAG, "${token.access_token}")
    }
}
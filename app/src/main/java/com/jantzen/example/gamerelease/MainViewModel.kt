package com.jantzen.example.gamerelease

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantzen.example.gamerelease.data.Repository
import com.jantzen.example.gamerelease.data.model.*
import com.jantzen.example.gamerelease.data.remote.GameAPI
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var repo = Repository(GameAPI)
    val gameRepo = repo.games




    init {
        Log.d(TAG, "init")
        loadGame()
    }

    fun loadGame() {
        viewModelScope.launch {
            repo.getGames("")
        }
    }

     }


package com.jantzen.example.gamerelease.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jantzen.example.gamerelease.data.model.Game_Release
import com.jantzen.example.gamerelease.data.remote.Game_ReleaseAPI

class Repository_Release(private val GRApi: Game_ReleaseAPI) {

    private val _releaseDate = MutableLiveData<List<Game_Release>>()
    val releaseDate: LiveData<List<Game_Release>>
        get() = _releaseDate

    suspend fun loadReleaseDate(){
        _releaseDate.value = GRApi.retrofitService.getRelease()


    }
}
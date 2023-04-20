package com.jantzen.example.gamerelease.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jantzen.example.gamerelease.data.model.*
import com.jantzen.example.gamerelease.data.remote.GameAPI

class Repository (private val gameApi: GameAPI) {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games

    private val _filteredGames = MutableLiveData<MutableList<Game>>(mutableListOf())
    val filteredGames: LiveData<MutableList<Game>>
    get() = _filteredGames

    private val _fullList = MutableLiveData<MutableList<Game>>(mutableListOf())
    val fullList: LiveData<MutableList<Game>>
    get() = _fullList

    private var searchedList = mutableListOf<Game>()

    suspend fun search(term: String) {
        searchedList.clear()
        for (games in games.value!!) {
            if (games.name!!.trim().lowercase().contains(term.trim().lowercase())){
                searchedList.add(games)
            }
        }
        _filteredGames.value = searchedList
    }

    suspend fun getFullList(){
        _filteredGames.value!!.clear()
        _games.value = _fullList.value
        _filteredGames.value!!.addAll(_games.value!!)
    }
    suspend fun getGames(filter: String){
        _filteredGames.value!!.clear()
       try {
           val response = gameApi.retrofitService.getGame()
           _games.value = response.results!!
           _fullList.value!!.addAll(response.results)
           _filteredGames.value!!.addAll(response.results)
       } catch (e: Exception){
           Log.e("repository getgames", "error api ${e}")
       }
    }

    suspend fun getFilteredGames(filter: String, keyWord: String){
        _filteredGames.value!!.clear()
        val response = games.value!!
        if (filter == "platform"){
            for (games in response){
                if (games.platforms != null) {
                    for (platform in games.platforms!!){
                        if (platform.name!!.contains(keyWord, ignoreCase = true)){
                            _filteredGames.value!!.add(games)
                        }
                    }
                }
            }
        }
        else if (filter == "year"){
            for (games in response){
                if (keyWord.toInt() == 2025){
                    if (games.expected_release_year!! >= keyWord.toInt()){
                        _filteredGames.value!!.add(games)
                    }
                }
                else if (games.expected_release_year == keyWord.toInt()){
                    _filteredGames.value!!.add(games)
                }
            }
        }
        else {
            Log.e("filterfunc", "NOFILTER!")
            try {
                _filteredGames.value = response.toMutableList()
            } catch (e: Exception){
                Log.e("repository getgames", "error api ${e}")
            }
        }
        _filteredGames.value = filteredGames.value
    }
}
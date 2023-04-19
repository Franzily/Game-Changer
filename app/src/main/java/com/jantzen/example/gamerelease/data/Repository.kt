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

    private var searchedList = mutableListOf<Game>()
    private var fullList = mutableListOf<Game>()

    suspend fun search(term: String) {
        searchedList.clear()
        for (games in games.value!!) {
            println(games.name)
            println(term)
            if (games.name!!.trim().lowercase().contains(term.trim().lowercase())){
                println(games.name)
                searchedList.add(games)
            }
        }
        println(searchedList)
        _filteredGames.value = searchedList
    }

    suspend fun getFullList(){
        _games.value = fullList.toList()
        _filteredGames.value = fullList
    }
    suspend fun getGames(filter: String){
       try {

           val response = gameApi.retrofitService.getGame()
           _games.value = response.results!!
           fullList.addAll(response.results)
           println(response.results)
       } catch (e: Exception){
           Log.e("repository getgames", "error api ${e}")
       }


    }

    suspend fun getFilteredGames(filter: String, keyWord: String){
        _filteredGames.value!!.clear()
        val response = games.value!! //gameApi.retrofitService.getGame()
        println(response)
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

// erstmal nicht benutzen
    suspend fun getFilteredGamesYear(filter: String, keyWord: String){
        //_filteredGames.value!!.clear()
        val response = games.value!!//gameApi.retrofitService.getGame()
        println("filter")
        if (filter == "year"){
            for (games in response){
                if (games.expected_release_year.toString() == keyWord){
                    _filteredGames.value!!.add(games)
                }


            }
            println("filteredGame")
            println(_filteredGames.value)


        }else {
            try {
                _filteredGames.value = response.toMutableList()
                println(response)
            } catch (e: Exception){
                Log.e("repository getgames", "error api ${e}")
            }
        }
        _filteredGames.value = filteredGames.value
    }




}
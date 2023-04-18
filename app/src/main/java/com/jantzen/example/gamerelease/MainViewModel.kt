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

    private val _gameSFav = MutableLiveData<MutableList<Game>>()
    val gamesFav : LiveData<MutableList<Game>>
    get() = _gameSFav

    fun inFav(game: Game):Boolean{
        for(games in _gameSFav.value!!) {
            if (games.id == game.id){
                return true
            }
        }
        return false
    }

    fun addFav(game: Game){
        if (!inFav(game)) {
            _gameSFav.value!!.add(game)
        }
        _gameSFav.value = _gameSFav.value
    }

    fun deleteFav(game: Game){
        for (i in _gameSFav.value!!.indices)
            if(game.id == _gameSFav.value!![i].id){
                _gameSFav.value!!.removeAt(i)
            }
        _gameSFav.value = _gameSFav.value
    }


    init {
        Log.d(TAG, "init")
        loadGame()
    }

    fun loadGame() {
        viewModelScope.launch {
            repo.getGames("")
        }
    }

    fun search(term: String){
        viewModelScope.launch {
            try {
                repo.search(term)
            }catch (e: Exception){
                Log.e("Game suche", "error loading Game ${e}")
            }
        }
    }
}


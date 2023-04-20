package com.jantzen.example.gamerelease

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
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

    private val _gameSFav = MutableLiveData<MutableList<Game>>(mutableListOf())
    val gamesFav : LiveData<MutableList<Game>>
    get() = _gameSFav


    fun inFav(game: Game):Boolean {
        try {
            for (games in _gameSFav.value!!) {
                if (games.id == game.id) {
                    return true
                }
            }
        }catch (e: Exception){
            return false
        }
        return false
    }

    fun addFav(game: Game){
        if (!inFav(game)) {
            _gameSFav.value?.add(game)
        }
        _gameSFav.value = _gameSFav.value
    }

    fun deleteFav(game: Game) {
        try {
            for (i in _gameSFav.value!!.indices)
                if (game.id == _gameSFav.value!![i].id) {
                    _gameSFav.value!!.removeAt(i)
                }
            _gameSFav.value = _gameSFav.value
        } catch (e:Exception){
            println("keine Favoriten")
        }
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

    fun loadFullGamesList(){
        viewModelScope.launch {
            repo.getFullList()
        }
    }

    fun loadFilteredGames(filter: String, keyWord: String){
        viewModelScope.launch {
            repo.getFilteredGames(filter, keyWord)
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


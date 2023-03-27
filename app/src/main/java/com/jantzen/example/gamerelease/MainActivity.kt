package com.jantzen.example.gamerelease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.jantzen.example.gamerelease.adapter.GameAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val navController = this.findNavController(R.id.nav_host_fragment)


    val adapter = GameAdapter()

}
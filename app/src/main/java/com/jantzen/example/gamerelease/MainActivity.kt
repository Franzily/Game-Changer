package com.jantzen.example.gamerelease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val uebersichtFragment = fragment_uebersicht()
    private val entdeckenFragment = Fragment_entdecken()
    private val favFragment = Fragment_favorite()
    val bottomNavigationsView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

    bottomNavigationsView.setOnItemSelectedListener{
        when(it.itemId){
            R.id.icon_entdecken ->replaceFragment(entdeckenFragment)
            R.id.icon_home -> replaceFragment(uebersichtFragment)
            R.id.icon_fav -> replaceFragment(favFragment)
        }
        true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    val navController = this.findNavController(R.id.nav_host_fragment)










    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragment)
            transaction.commit()
        }

    }
}
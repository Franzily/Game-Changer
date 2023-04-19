package com.jantzen.example.gamerelease


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jantzen.example.gamerelease.databinding.FragmentSplashBinding
import com.jantzen.example.gamerelease.databinding.FragmentUebersichtBinding


class Fragment_Splash : Fragment() {
    private lateinit var binding: FragmentSplashBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.fragment_uebersicht)
        }, 5000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(layoutInflater)

        return binding.root
    }
}
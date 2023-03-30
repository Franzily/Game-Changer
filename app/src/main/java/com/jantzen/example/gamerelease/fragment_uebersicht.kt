package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jantzen.example.gamerelease.adapter.GameAdapterFavoriten
import com.jantzen.example.gamerelease.databinding.FragmentFavoriteBinding
import com.jantzen.example.gamerelease.databinding.FragmentUebersichtBinding


class fragment_uebersicht : Fragment() {
    private lateinit var binding: FragmentUebersichtBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val name = requireArguments().getString("name")

        val adapter = GameAdapterFavoriten()
        //binding.materialToolbarUebersicht.title = name


    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uebersicht, container, false)
    }



    override fun onDestroyView() {
        super.onDestroyView()

    }


}
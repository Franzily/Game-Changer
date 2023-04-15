package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jantzen.example.gamerelease.adapter.GameAdapterFavoriten
import com.jantzen.example.gamerelease.databinding.FragmentFavoriteBinding


class Fragment_favorite : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val name = requireArguments().getString("name")

        val adapter = GameAdapterFavoriten()
        binding.RecyclerViewFav.adapter = adapter
       // binding.materialToolbarFav.title = name
        Navigation.findNavController(view).navigateUp()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

}
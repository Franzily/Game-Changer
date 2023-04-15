package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.jantzen.example.gamerelease.adapter.GameAdapterFavoriten
import com.jantzen.example.gamerelease.adapter.GameAdapterUebersicht
import com.jantzen.example.gamerelease.databinding.FragmentEntdeckenBinding


class Fragment_entdecken : Fragment() {
    private lateinit var binding: FragmentEntdeckenBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val name = requireArguments().getString("name")

        val adapter = GameAdapterFavoriten()
        binding.RecyclerViewEntdecken.adapter = adapter
        //binding.materialToolbarEntdecken.title = name
        Navigation.findNavController(view).navigateUp()

        //binding.materialToolbarEntdecken.


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entdecken, container, false)
    }


}
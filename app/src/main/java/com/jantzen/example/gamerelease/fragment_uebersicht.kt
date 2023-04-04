package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.jantzen.example.gamerelease.adapter.GameAdapterFavoriten
import com.jantzen.example.gamerelease.adapter.GameAdapterUebersicht
import com.jantzen.example.gamerelease.data.model.Game
import com.jantzen.example.gamerelease.databinding.FragmentFavoriteBinding
import com.jantzen.example.gamerelease.databinding.FragmentUebersichtBinding


class fragment_uebersicht : Fragment() {
    private lateinit var binding: FragmentUebersichtBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val name = requireArguments().getString("name")
       // val recyclerView = GameAdapterUebersicht(requireContext(), Game)
        //binding.recyclerViewUebersicht.adapter = recyclerView
        viewModel.token.observe(viewLifecycleOwner){
            viewModel.loadReleaseData("Bearer ${it.access_token}")
        }
        viewModel.releaseDates.observe(viewLifecycleOwner){
            binding.textView21.text = it.size.toString()
        }



        val adapter = GameAdapterFavoriten()
        //binding.materialToolbarUebersicht.title = name


    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        binding = FragmentUebersichtBinding.inflate(layoutInflater)
        return binding.root
    }





    override fun onDestroyView() {
        super.onDestroyView()

    }


}
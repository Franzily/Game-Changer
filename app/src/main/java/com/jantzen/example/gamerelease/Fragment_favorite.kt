package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.jantzen.example.gamerelease.adapter.GameAdapterFavoriten
import com.jantzen.example.gamerelease.databinding.FragmentFavoriteBinding



class Fragment_favorite : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val name = requireArguments().getString("name")

        val adapter = GameAdapterFavoriten(viewModel.gamesFav.value)
        binding.RecyclerViewFav.adapter = adapter
        binding.imageButtonBackFav.setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
       //TODO observer favoritenliste

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

}
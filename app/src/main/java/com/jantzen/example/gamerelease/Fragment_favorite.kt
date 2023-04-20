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
        if (viewModel.gamesFav.value != null) {

            val adapter = GameAdapterFavoriten(viewModel.gamesFav.value!!)
            binding.RecyclerViewFav.adapter = adapter
            viewModel.gamesFav.observe(viewLifecycleOwner) {
                adapter.submitList(it)}
        }else{
        }

            binding.imageButtonBackFav.setOnClickListener {
                Navigation.findNavController(view).navigateUp()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

}
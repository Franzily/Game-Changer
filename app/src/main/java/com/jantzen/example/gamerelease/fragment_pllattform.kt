package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.jantzen.example.gamerelease.databinding.FragmentGameBinding
import com.jantzen.example.gamerelease.databinding.FragmentPlattformBinding


class fragment_pllattform : Fragment() {
    private lateinit var binding: FragmentPlattformBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonBackPlatform.setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        val games = viewModel.gameRepo.value
        binding.cardviewPlaystation.setOnClickListener {

        }

        binding.cardviewPc.setOnClickListener {

        }

        binding.cardviewNintendo.setOnClickListener {

        }

        binding.cardviewXbox.setOnClickListener {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlattformBinding.inflate(layoutInflater)
        return binding.root
    }

}
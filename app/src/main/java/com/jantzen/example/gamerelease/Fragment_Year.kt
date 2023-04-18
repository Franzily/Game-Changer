package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.jantzen.example.gamerelease.databinding.FragmentGameBinding
import com.jantzen.example.gamerelease.databinding.FragmentYearBinding


class Fragment_Year : Fragment() {
    private lateinit var binding: FragmentYearBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButtonBackYear.setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        binding.cardView2025.setOnClickListener {

        }
        binding.cardView2024.setOnClickListener {

        }
        binding.cardView2023.setOnClickListener {

        }
        binding.cardView2022.setOnClickListener {

        }
        binding.cardView2021.setOnClickListener {

        }
        binding.cardView2020.setOnClickListener {

        }
        binding.cardView2019.setOnClickListener {

        }
        binding.cardView2018.setOnClickListener {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentYearBinding.inflate(layoutInflater)
        return binding.root
    }
}
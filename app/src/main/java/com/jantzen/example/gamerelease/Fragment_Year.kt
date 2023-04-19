package com.jantzen.example.gamerelease

import android.os.Bundle
import android.util.Log
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
            Log.d("onClick", "year 2025")
                Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2025"))
        }
        binding.cardView2024.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2024"))
        }
        binding.cardView2023.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2023"))
        }
        binding.cardView2022.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2022"))
        }
        binding.cardView2021.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2021"))
        }
        binding.cardView2020.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2020"))
        }
        binding.cardView2019.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2019"))
        }
        binding.cardView2018.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_YearDirections.actionFragmentYearToFragmentEntdecken("year", "2018"))
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
package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
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
            Navigation.findNavController(it).navigate(fragment_pllattformDirections.actionFragmentPlatformToFragmentEntdecken("platform", "playstation"))
        }

        binding.cardviewPc.setOnClickListener {
            Navigation.findNavController(it).navigate(fragment_pllattformDirections.actionFragmentPlatformToFragmentEntdecken("platform", "pc"))
        }

        binding.cardviewNintendo.setOnClickListener {
            Navigation.findNavController(it).navigate(fragment_pllattformDirections.actionFragmentPlatformToFragmentEntdecken("platform", "nintendo"))
        }

        binding.cardviewXbox.setOnClickListener {
            Navigation.findNavController(it).navigate(fragment_pllattformDirections.actionFragmentPlatformToFragmentEntdecken("platform", "xbox"))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlattformBinding.inflate(layoutInflater)
        return binding.root
    }

}
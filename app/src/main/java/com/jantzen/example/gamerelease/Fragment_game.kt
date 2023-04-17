package com.jantzen.example.gamerelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import coil.load
import com.jantzen.example.gamerelease.databinding.FragmentFavoriteBinding
import com.jantzen.example.gamerelease.databinding.FragmentGameBinding
import com.jantzen.example.gamerelease.databinding.FragmentUebersichtBinding


class Fragment_game : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Navigation.findNavController(view).navigateUp()
        val currentGame = requireArguments().getString("name")
        val ListGames = viewModel.gameRepo.value

        if (ListGames != null) {
            for (game in ListGames){
                if (game.name == currentGame){
                    binding.gameNameText.text = game.name
                    binding.gameDateText.text = game.expected_release_year.toString()
                    binding.gameDescriptionText.text = game.deck
                    binding.gamePlattformText.text = game.platforms.toString()
                    try {


                        if (game.image!!.medium_url != null) {
                            val imageURI = game.image.medium_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI)

                        } else if (game.image!!.super_url != null) {
                            val imageURI = game.image.super_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI)

                        } else if (game.image!!.small_url != null) {
                            val imageURI = game.image.small_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI)


                        } else if (game.image!!.original_url != null) {
                            val imageURI = game.image.original_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI)

                        }
                    }catch (e: Exception){
                        //TODO platzhalter einfügen
                    }

                }

            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

}
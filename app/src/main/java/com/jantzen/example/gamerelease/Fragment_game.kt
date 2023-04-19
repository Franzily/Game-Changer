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
import coil.size.Scale
import com.jantzen.example.gamerelease.data.model.Game
import com.jantzen.example.gamerelease.databinding.FragmentFavoriteBinding
import com.jantzen.example.gamerelease.databinding.FragmentGameBinding
import com.jantzen.example.gamerelease.databinding.FragmentUebersichtBinding


class Fragment_game : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val viewModel : MainViewModel by activityViewModels()
    var gameIsFav : Boolean = false
    lateinit var currentGame : Game


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonBackGame.setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

         currentGame = requireArguments().getParcelable<Game>("game")!!

        if (currentGame != null){
                    binding.gameNameText.text = currentGame.name
                    binding.gameDateText.text = currentGame.expected_release_year.toString()
            if (currentGame.description.isNullOrBlank()){
                binding.gameDescriptionText.text = "Beschreibung folgt"
            }else{
                binding.gameDescriptionText.text = currentGame.description
            }

                    binding.gamePlattformText.text = currentGame.platforms.toString()
                    gameIsFav = viewModel.inFav(currentGame)
                    setFav()
                    binding.imageButtonFav.setOnClickListener {
                        gameIsFav = !gameIsFav
                        setFav()

                    }
                    try {
                        if (currentGame.image!!.medium_url != null) {
                            val imageURI = currentGame.image!!.medium_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI){
                                scale(Scale.FILL)
                            }

                        } else if (currentGame.image!!.super_url != null) {
                            val imageURI = currentGame.image!!.super_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI){
                                scale(Scale.FILL)
                            }

                        } else if (currentGame.image!!.small_url != null) {
                            val imageURI = currentGame.image!!.small_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI){
                                scale(Scale.FILL)
                            }


                        } else if (currentGame.image!!.original_url != null) {
                            val imageURI = currentGame.image!!.original_url!!.toUri().buildUpon().scheme("https").build()
                            binding.gameImage.load(imageURI){
                                scale(Scale.FILL)
                            }
                        }
                    }catch (e: Exception){
                        //TODO platzhalter einfügen
                    }
                }
            }

    fun setFav(){

        if (gameIsFav) {
            binding.imageButtonFav.setImageResource(R.drawable.baseline_star_24)
            viewModel.addFav(currentGame!!)
        }else {
            binding.imageButtonFav.setImageResource(R.drawable.baseline_star_border_24)
            viewModel.deleteFav(currentGame!!)
        }
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }
}
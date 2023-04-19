package com.jantzen.example.gamerelease

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.PagerSnapHelper
import com.jantzen.example.gamerelease.adapter.GameAdapterEntdecken
import com.jantzen.example.gamerelease.adapter.GameAdapterFavoriten
import com.jantzen.example.gamerelease.adapter.GameAdapterUebersicht
import com.jantzen.example.gamerelease.databinding.FragmentEntdeckenBinding
import com.jantzen.example.gamerelease.databinding.FragmentUebersichtBinding


class Fragment_entdecken : Fragment() {
    private lateinit var binding: FragmentEntdeckenBinding
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardviewPlatform: CardView = view.findViewById(R.id.cardView_platform_entdecken)
        val cardviewYear: CardView = view.findViewById(R.id.cardview_year)


        val adapter = GameAdapterEntdecken()
        binding.RecyclerViewEntdecken.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.RecyclerViewEntdecken)


        binding.backbuttonEntdecken.setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        binding.cardViewPlatformEntdecken.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(Fragment_entdeckenDirections.actionFragmentEntdeckenToFragment2())
        }

        binding.cardviewYear.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(Fragment_entdeckenDirections.actionFragmentEntdeckenToFragmentKategorie())
        }


        viewModel.repo.filteredGames.observe(viewLifecycleOwner) {
            Log.d("observer", "games erhalten entdecken ${viewModel.repo.games.value!!.size}")
            adapter.submitList(it)
            Log.d("observer", "games erhalten entdecken ")
        }
        binding.imageButtonSearchEntdecken.setOnClickListener {
            if (binding.textInputLayoutSearch.visibility==View.GONE){
                binding.textInputLayoutSearch.visibility = View.VISIBLE
            }else {
                binding.textInputLayoutSearch.visibility = View.GONE
                var search = binding.searchInput.text
                println(search)
                viewModel.search(search.toString())
            }

        }
    }

    override fun onResume() {
        super.onResume()
        println("ist im RESUME")
        var filter = requireArguments().getString("filter")
        var key = requireArguments().getString("keyword")
        println(filter)
        println(key)
        if (filter != null) {
            if (filter.isNotEmpty() or filter.isNotBlank()) {
                try {
                    Log.d("entdecken", "$filter $key")
                } catch (e: Exception) {
                    Log.e("entdecken", "kein filter")
                }

                viewModel.loadFilteredGames(filter!!, key!!)
                this.arguments?.clear()
            }
            } else {
                println("reload Game")

                viewModel.loadFullGamesList()
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntdeckenBinding.inflate(layoutInflater)
        return binding.root
    }
}

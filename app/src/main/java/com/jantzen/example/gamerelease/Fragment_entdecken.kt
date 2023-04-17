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
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardviewPlatform: CardView = view.findViewById(R.id.cardView_platform_entdecken)
        val cardviewYear: CardView = view.findViewById(R.id.cardview_year)
        //val name = requireArguments().getString("name")


        binding.cardViewPlatformEntdecken.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_entdeckenDirections.actionFragmentEntdeckenToFragment2())
        }

        binding.cardviewYear.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_entdeckenDirections.actionFragmentEntdeckenToFragmentKategorie())
        }

        val adapter = GameAdapterEntdecken()
        binding.RecyclerViewEntdecken.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.RecyclerViewEntdecken)

        viewModel.repo.games.observe(viewLifecycleOwner) {
            Log.d("observer", "games erhalten entdecken ${viewModel.repo.games.value!!.size}")
            adapter.submitList(it)
        }
        //binding.materialToolbarEntdecken.title = name
        //Navigation.findNavController(view).navigateUp()

        //binding.materialToolbarEntdecken.


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
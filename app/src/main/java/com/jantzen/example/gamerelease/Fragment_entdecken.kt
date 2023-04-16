package com.jantzen.example.gamerelease

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        //val name = requireArguments().getString("name")

        val adapter = GameAdapterEntdecken()
        binding.RecyclerViewEntdecken.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.RecyclerViewEntdecken)

        viewModel.repo.games.observe(viewLifecycleOwner) {
            Log.d("observer", "games erhalten ${viewModel.repo.games.value!!.size}")
            adapter.submitList(it)
        }
        //binding.materialToolbarEntdecken.title = name
        Navigation.findNavController(view).navigateUp()

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
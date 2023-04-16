package com.jantzen.example.gamerelease

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.PagerSnapHelper
import com.jantzen.example.gamerelease.adapter.GameAdapterFavoriten
import com.jantzen.example.gamerelease.adapter.GameAdapterUebersicht
import com.jantzen.example.gamerelease.data.model.Game
import com.jantzen.example.gamerelease.databinding.FragmentFavoriteBinding
import com.jantzen.example.gamerelease.databinding.FragmentUebersichtBinding


class fragment_uebersicht : Fragment() {
    private lateinit var binding: FragmentUebersichtBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = GameAdapterUebersicht()
        binding.RecyclerViewUebersicht.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.RecyclerViewUebersicht)

        viewModel.repo.games.observe(viewLifecycleOwner) {
            Log.d("observer", "games erhalten ${viewModel.repo.games.value!!.size}")
            adapter.submitList(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUebersichtBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }


}
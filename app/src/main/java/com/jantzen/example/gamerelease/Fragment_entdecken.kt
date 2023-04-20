package com.jantzen.example.gamerelease

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.PagerSnapHelper
import com.jantzen.example.gamerelease.adapter.GameAdapterEntdecken
import com.jantzen.example.gamerelease.databinding.FragmentEntdeckenBinding


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
                hideKeyboard()
                var search = binding.searchInput.text
                viewModel.search(search.toString())
            }
        }
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun onResume() {
        super.onResume()
        var filter = requireArguments().getString("filter")
        var key = requireArguments().getString("keyword")
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
                viewModel.loadFullGamesList()
            Log.d("observer", "filteredGames erhalten entdecken ${viewModel.repo.filteredGames.value!!.size}")
            Log.d("observer", "fullGames erhalten entdecken ${viewModel.repo.fullList.value!!.size}")
            }

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntdeckenBinding.inflate(layoutInflater)
        return binding.root
    }
}

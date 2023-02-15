package com.example.museumapp.presentation.all_tours_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.museumapp.databinding.AllToursFragmentBinding
import com.example.museumapp.domain.model.Tour
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class AllToursFragment: Fragment() {

    private lateinit var binding: AllToursFragmentBinding
    private val viewModel: AllToursViewModel by viewModels()
    private lateinit var allToursAdapter: AllToursRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllToursFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupScreen()
    }


    private fun setupScreen() {
        viewModel.tourLiveData.observe(viewLifecycleOwner) { tours ->
            Log.e("FRAGMENTCHECK3", tours.toString())
            if (tours != null) {
                allToursAdapter = AllToursRecyclerViewAdapter(tours, this)
                Log.e("FRAGMENTCHECK2", tours.toString())
                binding.rvTours.apply {
                    adapter = allToursAdapter
                    layoutManager = LinearLayoutManager(activity)
                }
            }
        }
    }

    fun onTourItemClicked(tour: Tour) {
        val title = tour.title

        GlobalScope.launch {
            val tourExists = viewModel.getTourByTitle(title)
            if (tourExists != null) {
                Log.e("AAAAAAAAAAAAAAAA", "DELETED")
                viewModel.deleteTour(title)
                allToursAdapter.exists = false
            } else {
                Log.e("BBBBBBBBBBBBBBBBB", "INSERTED")
                viewModel.insertTour(tour)
                allToursAdapter.exists = true
            }
        }
    }

    fun initialCheck(tour: Tour) {
        val title = tour.title

        GlobalScope.launch {
            val tourExists = viewModel.getTourByTitle(title)
            allToursAdapter.exists = tourExists == null
        }
    }
}
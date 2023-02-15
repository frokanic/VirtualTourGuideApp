package com.example.museumapp.presentation.all_tours_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.museumapp.databinding.AllToursFragmentBinding
import com.example.museumapp.domain.model.Tour
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AllToursFragment: Fragment() {

    private lateinit var binding: AllToursFragmentBinding
    private val viewModel: AllToursViewModel by viewModels()
    private lateinit var allToursAdapter: AllToursRecyclerViewAdapter
    var exists = MutableLiveData<Boolean>()



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
        internetAccess()
    }

    private fun internetAccess() {
        viewModel.intAccess(requireContext())
        viewModel.internetAccess.observe(viewLifecycleOwner) {
            if (!it) {
                Toast.makeText(context, "Internet access was lost", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setupScreen() {
        viewModel.tourLiveData.observe(viewLifecycleOwner) { tours ->
            if (tours != null) {
                allToursAdapter = AllToursRecyclerViewAdapter(tours, this)
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
                viewModel.deleteTour(title)
                exists.postValue(false)
            } else {
                viewModel.insertTour(tour)
                exists.postValue(true)
            }
        }
    }

    fun initialCheck(tour: Tour) {
        val title = tour.title

        GlobalScope.launch {
            val tourExists = viewModel.getTourByTitle(title)
            if (tourExists == null) {
                exists.postValue(false)
            } else {
                exists.postValue(true)
            }
        }
    }
}
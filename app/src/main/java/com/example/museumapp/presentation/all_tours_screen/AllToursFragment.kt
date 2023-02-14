package com.example.museumapp.presentation.all_tours_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.museumapp.R
import com.example.museumapp.databinding.AllToursFragmentBinding
import com.example.museumapp.domain.model.Tour



class AllToursFragment: Fragment() {

    private lateinit var binding: AllToursFragmentBinding
    //private lateinit var viewModel: AllToursViewModel
    private val viewModel: AllToursViewModel by viewModels()
    lateinit var allToursAdapter: AllToursRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllToursFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(requireActivity()).get(AllToursViewModel::class.java)

        setupScreen(requireContext())
    }

    private fun setupScreen(context: Context) {
        var tours = viewModel.getToursFromRemote()
        if (tours != null && viewModel.isInternetConnected(context) && viewModel.checkInternetAccess(context)) {
            setupAllAllToursRecyclerView(tours)
        }
    }

    private fun setupAllAllToursRecyclerView(tours: List<Tour>) {
        allToursAdapter = AllToursRecyclerViewAdapter(tours)
        binding.rvTours.apply {
            adapter = allToursAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
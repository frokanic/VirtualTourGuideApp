package com.example.museumapp.presentation.all_tours_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.museumapp.databinding.AllToursFragmentBinding
import com.example.museumapp.network.NetworkConnectivityObserver
import com.example.museumapp.network.NetworkConnectivityObserverImpl

class AllToursFragment: Fragment() {

    private lateinit var binding: AllToursFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllToursFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
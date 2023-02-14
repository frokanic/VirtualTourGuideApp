package com.example.museumapp.presentation.all_tours_screen

import androidx.lifecycle.ViewModel
import com.example.museumapp.domain.repository.ToursRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllToursViewModel @Inject constructor(
    private val repository: ToursRepository
): ViewModel() {



}

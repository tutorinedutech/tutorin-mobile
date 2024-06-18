package com.dicoding.tutorinedutech.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tutorinedutech.data.repository.UserRepository

class SplashScreenVM(private val userRepository: UserRepository) : ViewModel() {

    fun getOnbaordingStatus() = userRepository.getOnboardingStatus().asLiveData()
}
package com.dicoding.tutorinedutech.ui.auth.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tutorinedutech.data.repository.UserRepository

class OnboardingVM(private val userRepository: UserRepository): ViewModel() {

    suspend fun disableOnboardingStatus() = userRepository.setOnboardingStatus(true)

    fun getOnboardingStatus() = userRepository.getOnboardingStatus().asLiveData()
}
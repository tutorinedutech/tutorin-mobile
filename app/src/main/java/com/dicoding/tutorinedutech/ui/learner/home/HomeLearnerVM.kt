package com.dicoding.tutorinedutech.ui.learner.home

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository

class HomeLearnerVM(private val learnerRepository: LearnerRepository) : ViewModel() {

    fun getHomeData() = learnerRepository.learnerHome()
}
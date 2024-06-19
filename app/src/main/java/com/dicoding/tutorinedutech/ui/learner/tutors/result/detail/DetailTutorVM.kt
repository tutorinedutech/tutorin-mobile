package com.dicoding.tutorinedutech.ui.learner.tutors.result.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository

class DetailTutorVM(private val learnerRepository: LearnerRepository) : ViewModel() {
    fun getDetailTutorById(tutorId: Int) = learnerRepository.detailTutorById(tutorId)
}
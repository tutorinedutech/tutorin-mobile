package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository

class DetailTutoringVM(private val learnerRepository: LearnerRepository) : ViewModel() {
    fun getDetailClassTutoring(sessionId: String) = learnerRepository.detailClassTutoring(sessionId)
}
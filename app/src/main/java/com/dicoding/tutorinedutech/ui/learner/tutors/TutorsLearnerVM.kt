package com.dicoding.tutorinedutech.ui.learner.tutors

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository

class TutorsLearnerVM(private val learnerRepository: LearnerRepository) : ViewModel() {

    fun getTutorsLearnerData() = learnerRepository.detailTutoring()
}
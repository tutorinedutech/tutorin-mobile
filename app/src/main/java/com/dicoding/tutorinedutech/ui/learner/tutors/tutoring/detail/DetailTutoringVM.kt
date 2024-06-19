package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository
import java.util.Date

class DetailTutoringVM(private val learnerRepository: LearnerRepository) : ViewModel() {
    fun getDetailClassTutoring(sessionId: String) = learnerRepository.detailClassTutoring(sessionId)

    fun updateDetailClassTutoring(
        timestamp: Date,
        location: String,
        classDetailId: Int
    ) = learnerRepository.detailDateTimeClassTutoring(timestamp, location, classDetailId)
}
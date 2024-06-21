package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.rating

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository

class RatingTutoringVM(private val learnerRepository: LearnerRepository) : ViewModel() {
    fun postRatingTutoring(
        rating: Int,
        comment: String,
        tutorId: Int
    ) = learnerRepository.writeReview(
        rating, comment,
        tutorId
    )
}
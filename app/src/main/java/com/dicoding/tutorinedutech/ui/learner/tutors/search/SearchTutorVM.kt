package com.dicoding.tutorinedutech.ui.learner.tutors.search

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository

class SearchTutorVM(private val learnerRepository: LearnerRepository) : ViewModel() {
    fun postSearchTutor(
        educationLevel: String,
        gender: String,
        domicile: String,
        language: String,
        learningMethod: String,
        day: Array<String>,
        time: Array<String>,
        subject: String
    ) = learnerRepository.searchTutor(
        educationLevel,
        gender,
        domicile,
        language,
        learningMethod,
        day,
        time,
        subject
    )
}
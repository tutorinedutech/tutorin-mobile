package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.validate

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.LearnerRepository

class ValidatePhotoVM(private val learnerRepository: LearnerRepository) : ViewModel() {
    fun updateDetailClass(detailClassId: Int) =
        learnerRepository.detailClassValidation(detailClassId)
}
package com.dicoding.tutorinedutech.ui.auth.register.main

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.db.learner.Learner
import com.dicoding.tutorinedutech.data.db.tutor.Tutor
import com.dicoding.tutorinedutech.data.repository.UserRepository

class RegisterMainVM(private val userRepository: UserRepository) : ViewModel() {


    fun saveRegisterTutorProgress(username: String, email: String, password: String) {
        val tutor = Tutor(
            id = 0,
            userId = 0,
            username = username,
            email = email,
            password = password
        )
        userRepository.saveCreatedTutorData(tutor)
    }

    fun saveRegisterLearnerProgress(username: String, email: String, password: String) {
        val learner = Learner(
            id = 0,
            userId = 0,
            username = username,
            email = email,
            password = password
        )
        userRepository.saveCreatedLearnerData(learner)
    }

    fun getRegisterLearnerData() = userRepository.getCreatedLeaernerData()
    fun getRegisterTutorData() = userRepository.getCreatedTutorData()
}
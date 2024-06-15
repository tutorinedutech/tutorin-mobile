package com.dicoding.tutorinedutech.ui.auth.register.learner

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.UserRepository

class RegisterLearnerVM(private val userRepository: UserRepository) : ViewModel() {

    fun uploadRegisterDataLearner(
        educationLevel: String,
        email: String,
        gender: String,
        domicile: String,
        name: String,
        password: String,
        phoneNumber: String,
        username: String,
    ) = userRepository.registerLearner(
        email = email,
        name = name,
        phoneNumber = phoneNumber,
        educationLevel = educationLevel,
        domicile = domicile,
        gender = gender,
        password = password,
        username = username,
    )

    fun getRegisterLearnerData() = userRepository.getCreatedLeaernerData()


}
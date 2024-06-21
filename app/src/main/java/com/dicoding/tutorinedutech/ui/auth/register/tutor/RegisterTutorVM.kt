package com.dicoding.tutorinedutech.ui.auth.register.tutor

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.db.tutor.TutorUpdateonCreate
import com.dicoding.tutorinedutech.data.repository.UserRepository

class RegisterTutorVM(private val userRepository: UserRepository) : ViewModel() {
    fun updateRegisterTutorProgress(
        nomorTelepon: String,
        pendidikan: String,
        gender: String,
        domisili: String,
        bahasa: String,
        kriteria: String,
        nama: String,
        accountNumber: String,
        metode: String
    ) {
        val tutor = TutorUpdateonCreate(
            id = 0,
            learningMethod = metode,
            accountNumber = accountNumber,
            userId = 0,
            name = nama,
            phoneNumber = nomorTelepon,
            educationLevel = pendidikan,
            gender = gender,
            domicile = domisili,
            languages = bahasa,
            teachingApproach = kriteria

        )
        userRepository.updateCreatedTutorData(tutor)
    }

    fun getRegisterTutorData() = userRepository.getCreatedTutorData()
}
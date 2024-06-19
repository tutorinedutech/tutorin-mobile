package com.dicoding.tutorinedutech.ui.tutor.home

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.TutoringRepository
import com.dicoding.tutorinedutech.data.repository.UserRepository

class HomeVM(private val userRepository: UserRepository, private val tutoringRepository: TutoringRepository): ViewModel() {
    fun getHomeData() = tutoringRepository.getHomeData()

    fun getUserData() = userRepository.getTutor()
}
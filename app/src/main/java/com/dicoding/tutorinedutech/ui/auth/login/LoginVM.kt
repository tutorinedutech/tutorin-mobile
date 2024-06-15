package com.dicoding.tutorinedutech.ui.auth.login

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.UserRepository

class LoginVM(private val userRepository: UserRepository) : ViewModel() {


    fun login(username: String, password: String) = userRepository.login(username, password)
}
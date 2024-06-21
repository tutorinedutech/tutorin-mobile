package com.dicoding.tutorinedutech.ui.auth.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.UserRepository

class LoginVM(private val userRepository: UserRepository) : ViewModel() {
    private val _loginStatus = MediatorLiveData<String>()
    val loginStatus = _loginStatus
    fun login(username: String, password: String) = userRepository.login(username, password)

    fun checkUserLoggedIn() {
        userRepository.checkLearnerIfLoggedIn(
            object : UserRepository.Companion.LoginCallback {
                override fun onLoginStatusChecked(isLoggedIn: Boolean) {
                    if (isLoggedIn) _loginStatus.value = "learner"
                }

            }
        )

        userRepository.checkTutorIfLoggedIn(
            object : UserRepository.Companion.LoginCallback {
                override fun onLoginStatusChecked(isLoggedIn: Boolean) {
                    if (isLoggedIn) _loginStatus.value = "tutor"
                }

            }
        )
    }
}
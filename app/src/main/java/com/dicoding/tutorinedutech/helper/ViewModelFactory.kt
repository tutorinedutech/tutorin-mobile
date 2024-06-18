package com.dicoding.tutorinedutech.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.dicoding.tutorinedutech.data.di.Injection
import com.dicoding.tutorinedutech.data.repository.UserRepository
import com.dicoding.tutorinedutech.ui.auth.login.LoginVM
import com.dicoding.tutorinedutech.ui.auth.onboarding.OnboardingVM
import com.dicoding.tutorinedutech.ui.auth.register.learner.RegisterLearnerVM
import com.dicoding.tutorinedutech.ui.auth.register.main.RegisterMainVM
import com.dicoding.tutorinedutech.ui.auth.register.tutor.RegisterTestVM
import com.dicoding.tutorinedutech.ui.auth.register.tutor.RegisterTutorVM
import com.dicoding.tutorinedutech.ui.auth.register.tutor.UploadKtpVM
import com.dicoding.tutorinedutech.ui.main.SplashScreenVM

class ViewModelFactory private constructor(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        with(modelClass) {
            when {
                isAssignableFrom(LoginVM::class.java) -> LoginVM(userRepository)
                isAssignableFrom(OnboardingVM::class.java) -> OnboardingVM(userRepository)
                isAssignableFrom(SplashScreenVM::class.java) -> SplashScreenVM(userRepository)
                isAssignableFrom(RegisterMainVM::class.java) -> RegisterMainVM(userRepository)
                isAssignableFrom(RegisterTestVM::class.java) -> RegisterTestVM(userRepository)
                isAssignableFrom(RegisterLearnerVM::class.java) -> RegisterLearnerVM(userRepository)
                isAssignableFrom(RegisterTutorVM::class.java) -> RegisterTutorVM(userRepository)
                isAssignableFrom(UploadKtpVM::class.java) -> UploadKtpVM(userRepository)

                else -> throw IllegalArgumentException("The viewmodel class: ${modelClass.name} has not been registered in ViewModelFactory")
            }
        } as T

    companion object : ViewModelProvider.Factory {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideRepository(context)
            )
        }.also { instance = it }
    }
}
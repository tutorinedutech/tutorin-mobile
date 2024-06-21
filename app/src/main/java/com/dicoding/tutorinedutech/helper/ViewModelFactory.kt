package com.dicoding.tutorinedutech.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.dicoding.tutorinedutech.data.di.Injection
import com.dicoding.tutorinedutech.data.repository.LearnerRepository
import com.dicoding.tutorinedutech.data.repository.TutoringRepository
import com.dicoding.tutorinedutech.data.repository.UserRepository
import com.dicoding.tutorinedutech.ui.auth.login.LoginVM
import com.dicoding.tutorinedutech.ui.auth.onboarding.OnboardingVM
import com.dicoding.tutorinedutech.ui.auth.register.learner.RegisterLearnerVM
import com.dicoding.tutorinedutech.ui.auth.register.main.RegisterMainVM
import com.dicoding.tutorinedutech.ui.auth.register.tutor.RegisterTestVM
import com.dicoding.tutorinedutech.ui.auth.register.tutor.RegisterTutorVM
import com.dicoding.tutorinedutech.ui.auth.register.tutor.UploadKtpVM
import com.dicoding.tutorinedutech.ui.learner.home.HomeLearnerVM
import com.dicoding.tutorinedutech.ui.learner.tutors.TutorsLearnerVM
import com.dicoding.tutorinedutech.ui.learner.tutors.result.detail.DetailTutorVM
import com.dicoding.tutorinedutech.ui.learner.tutors.search.SearchTutorVM
import com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.detail.DetailTutoringVM
import com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.rating.RatingTutoringVM
import com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.validate.ValidatePhotoVM
import com.dicoding.tutorinedutech.ui.main.SplashScreenVM
import com.dicoding.tutorinedutech.ui.tutor.detail_tentoring.DetailTentoringTutorVM
import com.dicoding.tutorinedutech.ui.tutor.home.HomeVM
import com.dicoding.tutorinedutech.ui.tutor.home.incoming.TabIncomingVM
import com.dicoding.tutorinedutech.ui.tutor.home.ongoing.TabOngoingVM
import com.dicoding.tutorinedutech.ui.tutor.profile.ProfileTutorVM
import com.dicoding.tutorinedutech.ui.tutor.profile.UpdatePasswordVM

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val learnerRepository: LearnerRepository,
    private val tutoringRepository: TutoringRepository
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
                isAssignableFrom(HomeVM::class.java) -> HomeVM(userRepository, tutoringRepository)
                isAssignableFrom(TabOngoingVM::class.java) -> TabOngoingVM(tutoringRepository)
                isAssignableFrom(TabIncomingVM::class.java) -> TabIncomingVM(tutoringRepository)
                isAssignableFrom(DetailTentoringTutorVM::class.java) -> DetailTentoringTutorVM(tutoringRepository)
                isAssignableFrom(ProfileTutorVM::class.java) -> ProfileTutorVM(userRepository)
                isAssignableFrom(UpdatePasswordVM::class.java) -> UpdatePasswordVM(userRepository)


                // Learner
                isAssignableFrom(HomeLearnerVM::class.java) -> HomeLearnerVM(learnerRepository)
                isAssignableFrom(TutorsLearnerVM::class.java) -> TutorsLearnerVM(learnerRepository)
                isAssignableFrom(SearchTutorVM::class.java) -> SearchTutorVM(learnerRepository)
                isAssignableFrom(ValidatePhotoVM::class.java) -> ValidatePhotoVM(learnerRepository)
                isAssignableFrom(DetailTutoringVM::class.java) -> DetailTutoringVM(learnerRepository)
                isAssignableFrom(DetailTutorVM::class.java) -> DetailTutorVM(learnerRepository)
                isAssignableFrom(RatingTutoringVM::class.java) -> RatingTutoringVM(learnerRepository)

                else -> throw IllegalArgumentException("The viewmodel class: ${modelClass.name} has not been registered in ViewModelFactory")
            }
        } as T

    companion object : ViewModelProvider.Factory {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideRepositoryUser(context),
                Injection.provideLearnerRepository(context),
                Injection.provideRepositoryTutoring(context)
            )
        }.also { instance = it }
    }
}
package com.dicoding.tutorinedutech.ui.tutor.profile

import android.net.Uri
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.UserRepository
import okhttp3.MultipartBody

class ProfileTutorVM(private val userRepository: UserRepository) : ViewModel() {
    private val _profileUri = MediatorLiveData<Uri?>()
    val profileUri = _profileUri

    private val _cvUri = MediatorLiveData<Uri?>()
    val cvUri = _cvUri
    fun getUserData() = userRepository.getTutor()

    fun fetchProfileData() = userRepository.getTutorData()

    fun updateProfileData(
        email: String,
        username: String,
        name: String,
        phoneNumber: String,
        educationLevel: String,
        gender: String,
        domicile: String,
        accountNumber: String,
        languages: String,
        teachingApproach: String,
        learningMethod: String,
    ) = userRepository.updateProfileTutor(
        email,
        username,
        name,
        phoneNumber,
        educationLevel,
        gender,
        domicile,
        accountNumber,
        languages,
        teachingApproach,
        learningMethod
    )

    fun setProfileUri(uri: Uri?) {
        _profileUri.value = uri
    }

    fun setCvUri(uri: Uri?) {
        _cvUri.value = uri
    }

    fun updateFile(cv: MultipartBody.Part?, profile: MultipartBody.Part?, type: String) =
        userRepository.updateFileTutor(cv, profile, type)
}
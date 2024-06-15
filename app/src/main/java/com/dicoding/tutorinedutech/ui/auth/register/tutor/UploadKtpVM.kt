package com.dicoding.tutorinedutech.ui.auth.register.tutor

import android.net.Uri
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.UserRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadKtpVM(private val userRepository: UserRepository) : ViewModel() {
    private val _imageUri = MediatorLiveData<Uri>()
    val imageUri = _imageUri
    fun getRegisterTutorData() = userRepository.getCreatedTutorData()

    fun uploadRegisterDataTutor(
        accountNumber: RequestBody,
        domicile: RequestBody,
        educationLevel: RequestBody,
        email: RequestBody,
        gender: RequestBody,
        ktp: MultipartBody.Part,
        languages: RequestBody,
        learningMethod: RequestBody,
        name: RequestBody,
        password: RequestBody,
        phoneNumber: RequestBody,
        teachingApproach: RequestBody,
        username: RequestBody,
    ) = userRepository.registerTutor(
        email = email,
        name = name,
        accountNumber = accountNumber,
        phoneNumber = phoneNumber,
        languages = languages,
        educationLevel = educationLevel,
        learningMethod = learningMethod,
        domicile = domicile,
        teachingApproach = teachingApproach,
        gender = gender,
        password = password,
        username = username,
        ktp = ktp,
    )

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }
}
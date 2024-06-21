package com.dicoding.tutorinedutech.ui.tutor.home.ongoing

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.TutoringRepository

class TabOngoingVM(private val tutoringRepository: TutoringRepository): ViewModel() {

    fun getOngoingClass() = tutoringRepository.getAllClass()

    fun getOngoingClassWithOneSession() = tutoringRepository.getAllClassWOneSession()
}
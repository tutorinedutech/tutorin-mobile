package com.dicoding.tutorinedutech.ui.tutor.home.incoming

import androidx.lifecycle.ViewModel
import com.dicoding.tutorinedutech.data.repository.TutoringRepository

class TabIncomingVM(private val tutoringRepository: TutoringRepository): ViewModel() {

    fun getIncomingClass() = tutoringRepository.getIncomingClass()

}
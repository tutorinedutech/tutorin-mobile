package com.dicoding.tutorinedutech.ui.tutor.detail_tentoring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.tutorinedutech.databinding.FragmentDetailTentoringTutorBinding

class DetailTentoringTutor : Fragment() {

    private var _binding: FragmentDetailTentoringTutorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailTentoringTutorBinding.inflate(inflater, container, false)
        return binding.root
    }
}
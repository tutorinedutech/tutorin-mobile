package com.dicoding.tutorinedutech.ui.tutor.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.tutorinedutech.databinding.FragmentProfileTutorBinding


class ProfileTutor : Fragment() {
    private var _binding: FragmentProfileTutorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileTutorBinding.inflate(inflater, container, false)
        return binding.root
    }
}
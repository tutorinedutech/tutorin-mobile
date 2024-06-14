package com.dicoding.tutorinedutech.ui.tutor.home.ongoing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentTabOngoingTutorBinding


class TabOngoingTutor : Fragment() {
    private var _binding: FragmentTabOngoingTutorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabOngoingTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

}
package com.dicoding.tutorinedutech.ui.tutor.home.incoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.tutorinedutech.databinding.FragmentTabIncomingTutorBinding


class TabIncomingTutor : Fragment() {
    private var _binding: FragmentTabIncomingTutorBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabIncomingTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

}
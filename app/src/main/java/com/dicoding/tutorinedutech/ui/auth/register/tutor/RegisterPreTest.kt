package com.dicoding.tutorinedutech.ui.auth.register.tutor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.tutorinedutech.databinding.FragmentRegisterPreTestBinding

class RegisterPreTest : Fragment() {

    private var _binding: FragmentRegisterPreTestBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterPreTestBinding.inflate(inflater, container, false)
        return binding.root
    }
}
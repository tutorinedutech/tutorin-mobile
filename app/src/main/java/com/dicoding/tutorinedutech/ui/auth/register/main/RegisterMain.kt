package com.dicoding.tutorinedutech.ui.auth.register.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.databinding.FragmentRegisterMainBinding

class RegisterMain : Fragment() {

    private var _binding: FragmentRegisterMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSelanjutnyaTutor.setOnClickListener {
                findNavController().navigate(RegisterMainDirections.actionRegisterMainToRegisterTutor())
            }
            btnSelanjutnyaLearner.setOnClickListener {
                findNavController().navigate(RegisterMainDirections.actionRegisterMainToRegisterLearner())
            }
            tvAskLoginTentor.setOnClickListener {
                findNavController().navigate(RegisterMainDirections.actionRegisterMainToLogin2())
            }
            tvAskLoginLearner.setOnClickListener {
                findNavController().navigate(RegisterMainDirections.actionRegisterMainToLogin2())
            }
        }
    }

}
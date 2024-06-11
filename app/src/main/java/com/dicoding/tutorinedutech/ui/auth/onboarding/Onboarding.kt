package com.dicoding.tutorinedutech.ui.auth.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.databinding.FragmentOnboardingBinding

class Onboarding : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnEnd.setOnClickListener {
                findNavController().navigate(OnboardingDirections.actionOnboardingToLogin2())
            }
            btnLewati.setOnClickListener {
                findNavController().navigate(OnboardingDirections.actionOnboardingToLogin2())
            }
        }
    }


}
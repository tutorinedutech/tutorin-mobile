package com.dicoding.tutorinedutech.ui.auth.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentOnboardingBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import kotlinx.coroutines.launch

class Onboarding : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var onboardingVM: OnboardingVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        onboardingVM = ViewModelProvider(this, factory)[OnboardingVM::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onboardingVM.getOnboardingStatus().observe(viewLifecycleOwner) { status ->
            if (status != null) {
                if (status) findNavController().navigate(R.id.action_global_login)
            }
        }
        binding.apply {
            btnEnd.setOnClickListener {
                findNavController().navigate(OnboardingDirections.actionOnboardingToLogin2())
                viewLifecycleOwner.lifecycleScope.launch {
                    onboardingVM.disableOnboardingStatus()
                }
            }
            btnLewati.setOnClickListener {
                findNavController().navigate(OnboardingDirections.actionOnboardingToLogin2())
                viewLifecycleOwner.lifecycleScope.launch {
                    onboardingVM.disableOnboardingStatus()
                }
            }
        }
    }


}
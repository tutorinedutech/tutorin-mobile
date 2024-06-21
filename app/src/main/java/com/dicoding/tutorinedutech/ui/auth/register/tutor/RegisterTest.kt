package com.dicoding.tutorinedutech.ui.auth.register.tutor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.databinding.FragmentRegisterTestBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory


class RegisterTest : Fragment() {
    private var _binding: FragmentRegisterTestBinding? = null
    private val binding get() = _binding!!
    private lateinit var registerTestVM: RegisterTestVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        registerTestVM = ViewModelProvider(this, factory)[RegisterTestVM::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSelesaikanTest.setOnClickListener {
                findNavController().navigate(RegisterTestDirections.actionRegisterTestToLoadingTestGradient())
            }
        }
    }
}
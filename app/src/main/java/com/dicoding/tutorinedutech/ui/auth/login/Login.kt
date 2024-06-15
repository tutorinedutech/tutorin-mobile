package com.dicoding.tutorinedutech.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.databinding.FragmentLoginBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory

class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginVM: LoginVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        loginVM = ViewModelProvider(this, factory)[LoginVM::class.java]

        binding.apply {
            tvAskLogin.setOnClickListener {
                findNavController().navigate(LoginDirections.actionLogin2ToRegisterMain())
            }
        }
    }
}
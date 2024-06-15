package com.dicoding.tutorinedutech.ui.auth.register.tutor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentRegisterTutorBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.ui.auth.register.main.RegisterMainVM

class RegisterTutor : Fragment() {
    private var _binding: FragmentRegisterTutorBinding? = null
    private val binding get() = _binding!!
    private lateinit var registerTutorVM: RegisterTutorVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        registerTutorVM = ViewModelProvider(this, factory)[RegisterTutorVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listTingkatPendidikan = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.tingkat_pendidikan)
        )

        binding.apply {
            actvTingkatPendidikan.setAdapter(listTingkatPendidikan)
        }
    }
}
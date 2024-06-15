package com.dicoding.tutorinedutech.ui.auth.register.learner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentRegisterLearnerBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory

class RegisterLearner : Fragment() {
    private var _binding: FragmentRegisterLearnerBinding? = null
    private val binding get() = _binding!!
    private lateinit var registerLearnerVM: RegisterLearnerVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterLearnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        registerLearnerVM = ViewModelProvider(this, factory)[RegisterLearnerVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listTingkatPendidikan = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.tingkat_pendidikan)
        )

        binding.apply {

            tbRegisterLearner.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {

                }
            }

            actvTingkatPendidikan.setAdapter(listTingkatPendidikan)

        }
    }
}
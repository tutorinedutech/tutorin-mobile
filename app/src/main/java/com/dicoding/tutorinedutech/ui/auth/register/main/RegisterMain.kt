package com.dicoding.tutorinedutech.ui.auth.register.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.databinding.FragmentRegisterMainBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.validateEmail
import com.dicoding.tutorinedutech.utils.validatePassword
import com.dicoding.tutorinedutech.utils.validateUsername

class RegisterMain : Fragment() {

    private var _binding: FragmentRegisterMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var registerMainVM: RegisterMainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        registerMainVM = ViewModelProvider(this, factory)[RegisterMainVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOVC()

        binding.apply {

            etEmailTutor.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("email_tutor", s?.validateEmail())
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etPasswordTutor.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("password_tutor", s?.validatePassword())
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etRePasswordTutor.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("re_password_tutor", null)
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etUsernameTutor.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("username_tutor", s?.validateUsername())
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etEmailLearner.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("email_learner", s?.validateEmail())
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etUsernameLearner.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("username_learner", s?.validateUsername())
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etPasswordLearner.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("password_learner", s?.validatePassword())
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etRePasswordLearner.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("re_password_learner", null)
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            btnSelanjutnyaTutor.setOnClickListener {
                registerMainVM.saveRegisterTutorProgress(
                    etUsernameTutor.text.toString(),
                    etEmailTutor.text.toString(),
                    etPasswordTutor.text.toString()
                )
                findNavController().navigate(RegisterMainDirections.actionRegisterMainToRegisterTutor())
            }
            btnSelanjutnyaLearner.setOnClickListener {
                registerMainVM.saveRegisterLearnerProgress(
                    etUsernameLearner.text.toString(),
                    etEmailLearner.text.toString(),
                    etPasswordLearner.text.toString()
                )
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

    fun initOVC() {
        binding.apply {
            btnSelanjutnyaTutor.isEnabled = false
            btnSelanjutnyaLearner.isEnabled = false
            tilEmailTutor.isErrorEnabled = true
            tilEmailLearner.isErrorEnabled = true
            tilPasswordTutor.isErrorEnabled = true
            tilPasswordLearner.isErrorEnabled = true
            tilUsernameTutor.isErrorEnabled = true
            tilUsernameLearner.isErrorEnabled = true
            tilRePasswordTutor.isErrorEnabled = true
            tilRePasswordLearner.isErrorEnabled = true

            tilEmailTutor.error = "Wajib diisi"
            tilEmailLearner.error = "Wajib diisi"
            tilPasswordTutor.error = "Wajib diisi"
            tilPasswordLearner.error = "Wajib diisi"
            tilUsernameTutor.error = "Wajib diisi"
            tilUsernameLearner.error = "Wajib diisi"
            tilRePasswordTutor.error = "Wajib diisi"
            tilRePasswordLearner.error = "Wajib diisi"

            registerMainVM.getRegisterTutorData().observe(viewLifecycleOwner) { tutor ->
                etUsernameTutor.setText(tutor?.username)
                etEmailTutor.setText(tutor?.email)
                etPasswordTutor.setText(tutor?.password)
                etRePasswordTutor.setText(tutor?.password)
            }

            registerMainVM.getRegisterLearnerData().observe(viewLifecycleOwner) { learner ->
                etUsernameLearner.setText(learner?.username)
                etEmailLearner.setText(learner?.email)
                etPasswordLearner.setText(learner?.password)
                etRePasswordLearner.setText(learner?.password)
            }
        }
    }


    fun showInputError(inputType: String, message: String?) {
        binding.apply {
            when (inputType) {
                "username_tutor" -> {
                    if (!message.isNullOrBlank()) {
                        tilUsernameTutor.isErrorEnabled = true
                        tilUsernameTutor.error = message
                    } else {
                        tilUsernameTutor.isErrorEnabled = false
                        tilUsernameTutor.error = null
                    }
                }

                "password_tutor" -> {
                    if (!message.isNullOrBlank()) {
                        tilPasswordTutor.isErrorEnabled = true
                        tilPasswordTutor.error = message
                    } else {
                        tilPasswordTutor.isErrorEnabled = false
                        tilPasswordTutor.error = null
                    }
                }

                "re_password_tutor" -> {
                    if (etPasswordTutor.text.toString() != etRePasswordTutor.text.toString()) {
                        tilRePasswordTutor.isErrorEnabled = true
                        tilRePasswordTutor.error = message
                    } else {
                        tilRePasswordTutor.isErrorEnabled = false
                        tilRePasswordTutor.error = null
                    }
                }

                "email_tutor" -> {
                    if (!message.isNullOrBlank()) {
                        tilEmailTutor.isErrorEnabled = true
                        tilEmailTutor.error = message
                    } else {
                        tilEmailTutor.isErrorEnabled = false
                        tilEmailTutor.error = null
                    }
                }

                "username_learner" -> {
                    if (!message.isNullOrBlank()) {
                        tilUsernameLearner.isErrorEnabled = true
                        tilUsernameLearner.error = message
                    } else {
                        tilUsernameLearner.isErrorEnabled = false
                        tilUsernameLearner.error = null
                    }
                }

                "password_learner" -> {
                    if (!message.isNullOrBlank()) {
                        tilPasswordLearner.isErrorEnabled = true
                        tilPasswordLearner.error = message
                    } else {
                        tilPasswordLearner.isErrorEnabled = false
                        tilPasswordLearner.error = null
                    }
                }

                "re_password_learner" -> {
                    if (etPasswordLearner.text.toString() != etRePasswordLearner.text.toString()) {
                        tilRePasswordLearner.isErrorEnabled = true
                        tilRePasswordLearner.error = message
                    } else {
                        tilRePasswordLearner.isErrorEnabled = false
                        tilRePasswordLearner.error = null
                    }
                }

                "email_learner" -> {
                    if (!message.isNullOrBlank()) {
                        tilEmailLearner.isErrorEnabled = true
                        tilEmailLearner.error = message
                    } else {
                        tilEmailLearner.isErrorEnabled = false
                        tilEmailLearner.error = null
                    }
                }
            }
            if ("learner" in inputType) {
                btnSelanjutnyaLearner.isEnabled =
                    !tilUsernameLearner.isErrorEnabled && !tilPasswordLearner.isErrorEnabled && !tilRePasswordLearner.isErrorEnabled && !tilEmailLearner.isErrorEnabled
            } else {
                btnSelanjutnyaTutor.isEnabled =
                    !tilUsernameTutor.isErrorEnabled && !tilPasswordTutor.isErrorEnabled && !tilRePasswordTutor.isErrorEnabled && !tilEmailTutor.isErrorEnabled
            }
        }
    }

}
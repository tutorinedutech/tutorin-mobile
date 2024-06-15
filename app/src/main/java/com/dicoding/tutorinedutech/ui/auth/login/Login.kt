package com.dicoding.tutorinedutech.ui.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentLoginBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.validatePassword
import com.dicoding.tutorinedutech.utils.validateUsername
import com.google.android.material.snackbar.Snackbar

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
            btnLogin.isEnabled = false
            tilUsername.isErrorEnabled = true
            tilPassword.isErrorEnabled = true

            tvAskLogin.setOnClickListener {
                findNavController().navigate(LoginDirections.actionLogin2ToRegisterMain())
            }

            etPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("password", s?.validatePassword())
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            etUsername.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("username", s?.validateUsername())
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            btnLogin.setOnClickListener {
                loginVM.login(etUsername.text.toString(), etPassword.text.toString())
                    .observe(viewLifecycleOwner) { result ->
                        if (result != null) {
                            when (result) {
                                is ResultState.Loading -> {
                                    btnLogin.text = null
                                    pbLogin.visibility = View.VISIBLE
                                }

                                is ResultState.Error -> {
                                    pbLogin.visibility = View.GONE
                                    btnLogin.text = resources.getString(R.string.btn_login)
                                    setSnackBar(Event(result.error))
                                }

                                is ResultState.Success -> {
                                    pbLogin.visibility = View.GONE
                                    btnLogin.text = resources.getString(R.string.btn_login)
//                                    findNavController()
//                                        .navigate(
//                                            LoginDirections.(
//                                            )
//                                        )
                                }
                            }
                        }
                    }
            }
        }
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }

    fun showInputError(inputType: String, message: String?) {
        binding.apply {
            when (inputType) {
                "username" -> {
                    if (!message.isNullOrBlank()) {
                        tilUsername.isErrorEnabled = true
                        tilUsername.error = message
                    } else {
                        tilUsername.isErrorEnabled = false
                        tilUsername.error = null
                    }
                }

                "password" -> {
                    if (!message.isNullOrBlank()) {
                        tilPassword.isErrorEnabled = true
                        tilPassword.error = message
                    } else {
                        tilPassword.isErrorEnabled = false
                        tilPassword.error = null
                    }
                }
            }
            btnLogin.isEnabled = !tilUsername.isErrorEnabled && !tilPassword.isErrorEnabled
        }
    }
}
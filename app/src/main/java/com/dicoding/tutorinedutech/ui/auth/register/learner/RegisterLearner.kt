package com.dicoding.tutorinedutech.ui.auth.register.learner

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentRegisterLearnerBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.validateMaxLength
import com.dicoding.tutorinedutech.utils.validatePhoneNumber
import com.google.android.material.snackbar.Snackbar

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
        initOVC()

        binding.apply {

            btnDaftar.setOnClickListener {
                finalRegisterLearner()
            }

            actvTingkatPendidikan.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("tingkat_pendidikan", s?.validateMaxLength(50))
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etNomorTelp.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("nomor_telp", s?.validatePhoneNumber())
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etDomisili.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("domisili", s?.validateMaxLength(300))
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etNama.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("nama", s?.validateMaxLength(100))
                }

                override fun afterTextChanged(s: Editable?) {}
            })


        }
    }

    fun initOVC() {
        binding.apply {
            btnDaftar.isEnabled = false
            tilNomorTelp.isErrorEnabled = true
            tilDomisili.isErrorEnabled = true
            tilPendidikan.isErrorEnabled = true
            tilNama.isErrorEnabled = true

            tilNomorTelp.error = "Wajib diisi"
            tilDomisili.error = "Wajib diisi"
            tilPendidikan.error = "Wajib diisi"
            tilNama.error = "Wajib diisi"

            registerLearnerVM.getRegisterLearnerData().observe(viewLifecycleOwner) { learner ->
                etNomorTelp.setText(learner?.phoneNumber)
                actvTingkatPendidikan.setText(learner?.educationLevel)
                if (learner?.gender == "Laki laki") rgJenisKelamin.check(rbLakiLaki.id) else rgJenisKelamin.check(
                    rbPerempuan.id
                )
                etDomisili.setText(learner?.domicile)
                etNama.setText(learner?.name)
            }

            val listTingkatPendidikan = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.tingkat_pendidikan)
            )

            actvTingkatPendidikan.setAdapter(listTingkatPendidikan)

            tbRegisterLearner.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }

    fun finalRegisterLearner() {
        binding.apply {
            registerLearnerVM.getRegisterLearnerData().observe(viewLifecycleOwner) { learner ->

                registerLearnerVM.uploadRegisterDataLearner(
                    gender = if (rgJenisKelamin.checkedRadioButtonId == rbLakiLaki.id) "Laki laki" else "Perempuan",
                    phoneNumber = etNomorTelp.text.toString(),
                    educationLevel = actvTingkatPendidikan.text.toString(),
                    domicile = etDomisili.text.toString(),
                    name = etNama.text.toString(),
                    password = learner?.password!!,
                    username = learner.username,
                    email = learner.email!!
                ).observe(viewLifecycleOwner) {result->if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            pbUpload.visibility = View.VISIBLE
                            btnDaftar.text = null
                        }

                        is ResultState.Error -> {
                            pbUpload.visibility = View.GONE
                            btnDaftar.text = resources.getText(R.string.btn_daftar)
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            pbUpload.visibility = View.GONE
                            btnDaftar.text = resources.getText(R.string.btn_daftar)
                            setSnackBar(Event("Akun Learner berhasil dibuat"))
                            findNavController().navigate(RegisterLearnerDirections.actionRegisterLearnerToLogin2())
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
                "nomor_telp" -> {
                    if (!message.isNullOrBlank()) {
                        tilNomorTelp.isErrorEnabled = true
                        tilNomorTelp.error = message
                    } else {
                        tilNomorTelp.isErrorEnabled = false
                        tilNomorTelp.error = null
                    }
                }

                "tingkat_pendidikan" -> {
                    if (!message.isNullOrBlank()) {
                        tilPendidikan.isErrorEnabled = true
                        tilPendidikan.error = message
                    } else {
                        tilPendidikan.isErrorEnabled = false
                        tilPendidikan.error = null
                    }
                }

                "domisili" -> {
                    if (!message.isNullOrBlank()) {
                        tilDomisili.isErrorEnabled = true
                        tilDomisili.error = message
                    } else {
                        tilDomisili.isErrorEnabled = false
                        tilDomisili.error = null
                    }
                }

                "nama" -> {
                    if (!message.isNullOrBlank()) {
                        tilNama.isErrorEnabled = true
                        tilNama.error = message
                    } else {
                        tilNama.isErrorEnabled = false
                        tilNama.error = null
                    }
                }
            }

            btnDaftar.isEnabled =
                !tilNama.isErrorEnabled && !tilDomisili.isErrorEnabled && !tilNomorTelp.isErrorEnabled && !tilPendidikan.isErrorEnabled && (rgJenisKelamin.checkedRadioButtonId != -1)
        }
    }
}
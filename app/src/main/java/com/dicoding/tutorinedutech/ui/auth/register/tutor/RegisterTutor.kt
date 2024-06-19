package com.dicoding.tutorinedutech.ui.auth.register.tutor

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
import com.dicoding.tutorinedutech.databinding.FragmentRegisterTutorBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.validateMaxLength
import com.dicoding.tutorinedutech.utils.validatePhoneNumber

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
        initOVC()

        binding.apply {

            btnDaftar.setOnClickListener {
                registerTutorVM.updateRegisterTutorProgress(
                    gender = if (rgJenisKelamin.checkedRadioButtonId == rbLakiLaki.id) "Laki laki" else "Perempuan",
                    nomorTelepon = etNomorTelp.text.toString(),
                    pendidikan = actvTingkatPendidikan.text.toString(),
                    bahasa = etBahasa.text.toString(),
                    domisili = etDomisili.text.toString(),
                    kriteria = etKriteria.text.toString(),
                    accountNumber = etNomorAkun.text.toString(),
                    nama = etNama.text.toString(),
                    metode = etMetode.text.toString()
                )
                findNavController().navigate(RegisterTutorDirections.actionRegisterTutorToRegisterPreTest())
            }


            etBahasa.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("bahasa", s?.validateMaxLength(100))
                }

                override fun afterTextChanged(s: Editable?) {}

            })

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

            etMetode.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("metode", s?.validateMaxLength(300))
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

            etKriteria.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("kriteria", s?.validateMaxLength(190))
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

            etNomorAkun.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("nomor_akun", s?.validateMaxLength(60))
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }
    }

    fun initOVC() {
        binding.apply {
            btnDaftar.isEnabled = false
            tilNomorTelp.isErrorEnabled = true
            tilBahasa.isErrorEnabled = true
            tilDomisili.isErrorEnabled = true
            tilKriteria.isErrorEnabled = true
            tilPendidikan.isErrorEnabled = true
            tilMetode.isErrorEnabled = true
            tilNama.isErrorEnabled = true
            tilNomorAkun.isErrorEnabled = true

            tilNomorTelp.error = "Wajib diisi"
            tilBahasa.error = "Wajib diisi"
            tilDomisili.error = "Wajib diisi"
            tilKriteria.error = "Wajib diisi"
            tilPendidikan.error = "Wajib diisi"
            tilMetode.error = "Wajib diisi"
            tilNama.error = "Wajib diisi"
            tilNomorAkun.error = "Wajib diisi"

            registerTutorVM.getRegisterTutorData().observe(viewLifecycleOwner) { tutor ->
                etNomorTelp.setText(tutor?.phoneNumber)
                actvTingkatPendidikan.setText(tutor?.educationLevel)
                if (tutor?.gender == "Laki laki") rgJenisKelamin.check(rbLakiLaki.id) else rgJenisKelamin.check(
                    rbPerempuan.id
                )
                etDomisili.setText(tutor?.domicile)
                etBahasa.setText(tutor?.languages)
                etMetode.setText(tutor?.learningMethod)
                etKriteria.setText(tutor?.teachingApproach)
                etNama.setText(tutor?.name)
                etNomorAkun.setText(tutor?.accountNumber)
            }

            val listTingkatPendidikan = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.tingkat_pendidikan)
            )

            actvTingkatPendidikan.setAdapter(listTingkatPendidikan)

            tbRegisterTutor.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }
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

                "jenis_kelamin" -> {
                    if (rgJenisKelamin.isSelected) {
                        tvRgError.visibility = View.GONE
                    } else {
                        tvRgError.visibility = View.VISIBLE
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

                "bahasa" -> {
                    if (!message.isNullOrBlank()) {
                        tilBahasa.isErrorEnabled = true
                        tilBahasa.error = message
                    } else {
                        tilBahasa.isErrorEnabled = false
                        tilBahasa.error = null
                    }
                }

                "metode" -> {
                    if (!message.isNullOrBlank()) {
                        tilMetode.isErrorEnabled = true
                        tilMetode.error = message
                    } else {
                        tilMetode.isErrorEnabled = false
                        tilMetode.error = null
                    }
                }

                "kriteria" -> {
                    if (!message.isNullOrBlank()) {
                        tilKriteria.isErrorEnabled = true
                        tilKriteria.error = message
                    } else {
                        tilKriteria.isErrorEnabled = false
                        tilKriteria.error = null
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

                "nomor_akun" -> {
                    if (!message.isNullOrBlank()) {
                        tilNomorAkun.isErrorEnabled = true
                        tilNomorAkun.error = message
                    } else {
                        tilNomorAkun.isErrorEnabled = false
                        tilNomorAkun.error = null
                    }
                }
            }

            btnDaftar.isEnabled =
                !tilNomorAkun.isErrorEnabled && !tilNama.isErrorEnabled && !tilBahasa.isErrorEnabled && !tilMetode.isErrorEnabled && !tilDomisili.isErrorEnabled && !tilKriteria.isErrorEnabled && !tilNomorTelp.isErrorEnabled && !tilPendidikan.isErrorEnabled && (rgJenisKelamin.checkedRadioButtonId != -1)
        }
    }
}
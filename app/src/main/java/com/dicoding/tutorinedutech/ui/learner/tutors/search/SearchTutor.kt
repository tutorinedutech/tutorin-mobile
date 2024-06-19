package com.dicoding.tutorinedutech.ui.learner.tutors.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentSearchTutorBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.validateMaxLength
import com.google.android.material.snackbar.Snackbar

class SearchTutor : Fragment() {
    private var _binding: FragmentSearchTutorBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchTutorVM: SearchTutorVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        searchTutorVM = ViewModelProvider(this, factory)[SearchTutorVM::class.java]
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

            tbSearchTutor.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            // TODO: update the color of button when disabled
            btnSearchTutor.isEnabled = false
            tilPendidikan.isErrorEnabled = true
            tilDomisili.isErrorEnabled = true
            tilBahasa.isErrorEnabled = true
            tilMataPelajaran.isErrorEnabled = true
            tilMetode.isErrorEnabled = true
            tilKriteria.isErrorEnabled = true

            tilPendidikan.error = "Wajib diisi"
            tilDomisili.error = "Wajib diisi"
            tilBahasa.error = "Wajib diisi"
            tilMataPelajaran.error = "Wajib diisi"
            tilMetode.error = "Wajib diisi"
            tilKriteria.error = "Wajib diisi"

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

            etSubject.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("mata_pelajaran", s?.validateMaxLength(50))
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

            etKriteria.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    showInputError("kriteria", s?.validateMaxLength(500))
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            btnSearchTutor.setOnClickListener {
                val selectedDays = getSelectedDays()
                val selectedTimes = getSelectedTime()

                searchTutorVM.postSearchTutor(
                    educationLevel = actvTingkatPendidikan.text.toString(),
                    gender = if (rgJenisKelamin.checkedRadioButtonId == rbLakiLaki.id) "Laki laki" else "Perempuan",
                    domicile = etDomisili.text.toString(),
                    language = etBahasa.text.toString(),
                    learningMethod = etMetode.text.toString(),
                    subject = etSubject.text.toString(),
                    day = selectedDays,
                    time = selectedTimes,
                ).observe(viewLifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is ResultState.Loading -> {
                                pbSearchTutor.visibility = View.VISIBLE
                                btnSearchTutor.text = null
                            }

                            is ResultState.Error -> {
                                pbSearchTutor.visibility = View.GONE
                                btnSearchTutor.text = resources.getText(R.string.btn_search_tutor)
                                setSnackBar(Event(result.error))
                            }

                            is ResultState.Success -> {
                                pbSearchTutor.visibility = View.GONE
                                btnSearchTutor.text = resources.getText(R.string.btn_search_tutor)

                                Log.d("token", result.toString())
                                Log.d("token", selectedDays.joinToString())

//                                findNavController().navigate(RegisterLearnerDirections.actionRegisterLearnerToLogin2())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getSelectedDays(): Array<String> {
        val selectedDays = mutableListOf<String>()

        binding.apply {
            if (cbSenin.isChecked) selectedDays.add("monday")
            if (cbSelasa.isChecked) selectedDays.add("tuesday")
            if (cbRabu.isChecked) selectedDays.add("wednesday")
            if (cbKamis.isChecked) selectedDays.add("thursday")
            if (cbJumat.isChecked) selectedDays.add("friday")
            if (cbSabtu.isChecked) selectedDays.add("saturday")
            if (cbMinggu.isChecked) selectedDays.add("sunday")

            if (selectedDays.isEmpty()) {
                tvCbPilihanHariError.visibility = View.VISIBLE
            } else {
                tvCbPilihanHariError.visibility = View.GONE
            }

            return selectedDays.toTypedArray()
        }
    }

    private fun getSelectedTime(): Array<String> {
        val selectedTime = mutableListOf<String>()

        binding.apply {
            if (cbClock1.isChecked) selectedTime.add("09.00")
            if (cbClock2.isChecked) selectedTime.add("11.00")
            if (cbClock3.isChecked) selectedTime.add("13.00")
            if (cbClock4.isChecked) selectedTime.add("15.00")
            if (cbClock5.isChecked) selectedTime.add("17.00")
            if (cbClock6.isChecked) selectedTime.add("19.00")

            if (selectedTime.isEmpty()) {
                tvCbPilihanHariError.visibility = View.VISIBLE
            } else {
                tvCbPilihanHariError.visibility = View.GONE
            }

            return selectedTime.toTypedArray()
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

                "bahasa" -> {
                    if (!message.isNullOrBlank()) {
                        tilBahasa.isErrorEnabled = true
                        tilBahasa.error = message
                    } else {
                        tilBahasa.isErrorEnabled = false
                        tilBahasa.error = null
                    }
                }

                "mata_pelajaran" -> {
                    if (!message.isNullOrBlank()) {
                        tilMataPelajaran.isErrorEnabled = true
                        tilMataPelajaran.error = message
                    } else {
                        tilMataPelajaran.isErrorEnabled = false
                        tilMataPelajaran.error = null
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
            }

            btnSearchTutor.isEnabled =
                !tilDomisili.isErrorEnabled && !tilBahasa.isErrorEnabled && !tilMetode.isErrorEnabled && !tilKriteria.isErrorEnabled && !tilPendidikan.isErrorEnabled && !tilMataPelajaran.isErrorEnabled && (rgJenisKelamin.checkedRadioButtonId != -1)
        }
    }
}
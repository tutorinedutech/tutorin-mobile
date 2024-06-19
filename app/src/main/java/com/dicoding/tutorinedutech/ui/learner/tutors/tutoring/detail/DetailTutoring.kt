package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.detail

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.data.response.DetailClassData
import com.dicoding.tutorinedutech.databinding.FragmentDetailTutoringLearnerBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.ValidationPhotoData
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DetailTutoring : Fragment() {
    private var _binding: FragmentDetailTutoringLearnerBinding? = null

    private val binding get() = _binding!!
    private lateinit var detailTutoringVM: DetailTutoringVM

    private lateinit var sessionId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTutoringLearnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        detailTutoringVM = ViewModelProvider(this, factory)[DetailTutoringVM::class.java]

        val layoutLineaManager = LinearLayoutManager(requireContext())
        binding.rvListTutoring.layoutManager = layoutLineaManager

        sessionId = DetailTutoringArgs.fromBundle(arguments as Bundle).sessionId

        binding.apply {

            tbDetailTutoring.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            fetchDetailTutoring(sessionId)
        }
    }

    private fun fetchDetailTutoring(sessionId: String) {
        binding.apply {
            detailTutoringVM.getDetailClassTutoring(sessionId)
                .observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            pbDetailTutoring.visibility = View.VISIBLE
                            layoutSessionValidate.visibility = View.GONE
                            layoutTutorProfile.visibility = View.GONE
                        }

                        is ResultState.Error -> {
                            pbDetailTutoring.visibility = View.GONE
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            pbDetailTutoring.visibility = View.GONE
                            layoutTutorProfile.visibility = View.VISIBLE

                            setDetailTutoringData(result.data.data)
                        }
                    }
                }
        }
    }

    private fun setDetailTutoringData(data: DetailClassData) {
        binding.apply {
            val notValidatedDetails =
                data.classDetails.filter { it.validationStatus == "not validated" }

            if (notValidatedDetails.isNotEmpty()) {
                layoutSessionValidate.visibility = View.VISIBLE

                layoutSessionValidate.setOnClickListener {
                    val firstNotValidatedDetail = notValidatedDetails.first()
                    val validationData = ValidationPhotoData(
                        id = firstNotValidatedDetail.id,
                        nameTutor = data.nameTutor,
                        usernameTutor = data.usernameTutor,
                        subject = data.subject,
                        timestamp = firstNotValidatedDetail.timestamp,
                        proofImageLink = firstNotValidatedDetail.proofImageLink,
                        session = firstNotValidatedDetail.session,
                        location = firstNotValidatedDetail.location
                    )

                    findNavController().navigate(
                        DetailTutoringDirections.actionDetailTutoringToValidatePhoto(
                            validationData
                        )
                    )
                }
            } else {
                layoutSessionValidate.visibility = View.GONE
            }

            tvTutorName.text = data.nameTutor
            tvTutorUsername.text = "@${data.usernameTutor}"
            tvTutorTopic.text = data.subject

            val adapter = TutoringAdapter()
            adapter.submitList(data.classDetails)
            binding.rvListTutoring.adapter = adapter

            adapter.setOnClickCallback(object : TutoringAdapter.OnItemClickCallback {
                override fun onItemClicked(classDetailId: Int) {
                    showDateTimeLocationDialog { dateTime, location ->
                        detailTutoringVM.updateDetailClassTutoring(
                            timestamp = dateTime,
                            location = location,
                            classDetailId = classDetailId
                        ).observe(viewLifecycleOwner) { result ->
                            when (result) {
                                is ResultState.Loading -> {
                                    setSnackBar(Event("Sedang memperbarui.."))
                                }

                                is ResultState.Error -> {
                                    setSnackBar(Event(result.error))
                                }

                                is ResultState.Success -> {
                                    setSnackBar(Event("Berhasil memperbarui waktu dan lokasi"))
                                    fetchDetailTutoring(sessionId)
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    fun showDateTimeLocationDialog(onDataSelected: (dateTime: Date, location: String) -> Unit) {
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.dialog_layout, null)

        // TODO: setting validation
        val editTextDateTime = dialogView.findViewById<EditText>(R.id.et_date_time)
        val editTextLocation = dialogView.findViewById<EditText>(R.id.et_location)

        val currentDateTime = Calendar.getInstance()

        // Handle Date and Time selection
        editTextDateTime.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)

                    TimePickerDialog(
                        requireContext(),
                        { _, hourOfDay, minute ->
                            selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            selectedDate.set(Calendar.MINUTE, minute)

                            val dateFormat =
                                SimpleDateFormat("dd MMMM yyyy HH.mm", Locale.getDefault())
                            editTextDateTime.setText(dateFormat.format(selectedDate.time))
                        },
                        9,
                        0,
                        true
                    ).show()
                },
                currentDateTime.get(Calendar.YEAR),
                currentDateTime.get(Calendar.MONTH),
                currentDateTime.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Show dialog
        AlertDialog.Builder(requireContext(), R.style.CustomAlertDialogTheme)
            .setTitle("Masukkan Tanggal, Waktu, dan Lokasi")
            .setView(dialogView)
            .setPositiveButton("Ok") { dialog, _ ->
                val dateTimeText = editTextDateTime.text.toString()
                val locationText = editTextLocation.text.toString()

                val dateFormat = SimpleDateFormat("dd MMMM yyyy HH.mm", Locale.getDefault())
                val dateTime = dateFormat.parse(dateTimeText) ?: Date()

                onDataSelected(dateTime, locationText)
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class DetailTutoring : Fragment() {
    private var _binding: FragmentDetailTutoringLearnerBinding? = null

    private val binding get() = _binding!!
    private lateinit var detailTutoringVM: DetailTutoringVM

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

        val sessionId = DetailTutoringArgs.fromBundle(arguments as Bundle).sessionId

        binding.apply {

            tbDetailTutoring.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

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
        }
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}
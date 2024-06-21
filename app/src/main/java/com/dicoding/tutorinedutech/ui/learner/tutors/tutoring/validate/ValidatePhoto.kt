package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.validate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentValidatePhotoBinding
import com.dicoding.tutorinedutech.helper.Helper
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.ValidationPhotoData
import com.google.android.material.snackbar.Snackbar

class ValidatePhoto : Fragment() {

    private var _binding: FragmentValidatePhotoBinding? = null

    private val binding get() = _binding!!
    private lateinit var validatePhotoVM: ValidatePhotoVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentValidatePhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        validatePhotoVM = ViewModelProvider(this, factory)[ValidatePhotoVM::class.java]

        val validationData: ValidationPhotoData =
            ValidatePhotoArgs.fromBundle(arguments as Bundle).validationData

        binding.apply {
            tbValidatePhoto.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            tvTutorName.text = validationData.nameTutor
            tvTutorUsername.text = "@${validationData.usernameTutor}"
            tvTutorTopic.text = validationData.subject
            Glide.with(requireContext())
                .load(validationData.proofImageLink)
                .into(ivValidationPhoto)
            tvSessionTitle.text = getString(R.string.session_number, validationData.session)
            tvSessionLocation.text = validationData.location
            tvSessionDateTime.text = Helper.formatDateTime(validationData.timestamp)

            btnAccept.setOnClickListener {
                validatePhotoVM.updateDetailClass(validationData.id)
                    .observe(viewLifecycleOwner) { result ->
                        when (result) {
                            is ResultState.Loading -> {
                                pbValidatePhoto.visibility = View.VISIBLE
                                layoutValidatePhoto.visibility = View.GONE
                            }

                            is ResultState.Error -> {
                                pbValidatePhoto.visibility = View.GONE
                                setSnackBar(Event(result.error))
                            }

                            is ResultState.Success -> {
                                pbValidatePhoto.visibility = View.GONE

                                setSnackBar(Event("Validasi kehadiran berhasil diubah"))
                                findNavController().navigate(ValidatePhotoDirections.actionValidatePhotoToTutorsLearner())
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
}
package com.dicoding.tutorinedutech.ui.learner.tutors.result.detail

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
import com.dicoding.tutorinedutech.data.response.TutorData
import com.dicoding.tutorinedutech.databinding.FragmentDetailTutorBinding
import com.dicoding.tutorinedutech.helper.Helper
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailTutor : Fragment() {
    private var _binding: FragmentDetailTutorBinding? = null

    private val binding get() = _binding!!
    private lateinit var detailTutorVM: DetailTutorVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        detailTutorVM = ViewModelProvider(this, factory)[DetailTutorVM::class.java]

        binding.apply {
            tbDetailResultTutor.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            // TODO: update tutor id
            detailTutorVM.getDetailTutorById(1).observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            pbDetailTutor.visibility = View.VISIBLE
                            layoutDetailTutor.visibility = View.GONE
                        }

                        is ResultState.Error -> {
                            pbDetailTutor.visibility = View.GONE
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            pbDetailTutor.visibility = View.GONE
                            layoutDetailTutor.visibility = View.VISIBLE

                            setDetailTutorData(result.data.data)
                        }
                    }
                }
            }
        }
    }

    private fun setDetailTutorData(data: TutorData) {
        binding.apply {
            val subjects = data.availabilities.map { it.subject }.toSet()

            Glide.with(requireContext())
                .load(data.profilePicture)
                .into(ivTutorProfile)
            tvTutorName.text = data.name
            tvTutorRating.text = "${data.averageRating}/5"
            tvTutorEducation.text = data.educationLevel
            tvTutorGender.text = data.gender
            tvTutorDomicile.text = data.domicile
            tvTutorLanguage.text = data.languages
            tvTutorTopic.text = subjects.joinToString(", ")
            tvMethodTeaching.text = data.learningMethod

            CoroutineScope(Dispatchers.Main).launch {
                pbLoadingPdf.visibility = View.VISIBLE
                ivTutorCv.visibility = View.GONE

                val pdfBitmap = withContext(Dispatchers.IO) {
                    Helper.downloadAndRenderPdf(requireContext(), data.cv)
                }

                pbLoadingPdf.visibility = View.GONE
                ivTutorCv.visibility = View.VISIBLE

                if (pdfBitmap != null) {
                    Glide.with(requireContext())
                        .load(pdfBitmap)
                        .into(ivTutorCv)
                } else {
                    setSnackBar(Event("Error Rendering PDF"))
                }
            }

            btnChoose.setOnClickListener {
                findNavController().navigate(DetailTutorDirections.actionDetailTutorToRatingTutoring())
            }
        }
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}
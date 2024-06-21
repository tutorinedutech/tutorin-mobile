package com.dicoding.tutorinedutech.ui.tutor.detail_tentoring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.tutorinedutech.databinding.FragmentDetailTentoringTutorBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory

class DetailTentoringTutor : Fragment() {

    private var _binding: FragmentDetailTentoringTutorBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailTentoringTutorVM: DetailTentoringTutorVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailTentoringTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        detailTentoringTutorVM =
            ViewModelProvider(this, factory)[DetailTentoringTutorVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idClass = DetailTentoringTutorArgs.fromBundle(arguments as Bundle).classess
        val status = DetailTentoringTutorArgs.fromBundle(arguments as Bundle).status

        binding.apply {
            when (status) {
                "incoming" -> {
                    tvDescPembayaran.visibility = View.GONE
                    cvNominalHarga.visibility = View.GONE
                    tvDescTutoring.visibility = View.GONE
                    rvTutoring.visibility = View.GONE
                    tvDescRating.visibility = View.GONE
                    cvRatingLearner.visibility = View.GONE
                    tvSelesai.visibility = View.GONE
                    uploadLearningProof.visibility = View.GONE
                }

                "ongoing" -> {
                    tvDescPenerimaan.visibility = View.GONE
                    lvPilihan.visibility = View.GONE
                }
            }

            uploadLearningProof.setOnClickListener {

            }
        }
    }
}
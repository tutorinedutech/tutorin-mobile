package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.rating

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentRatingTutoringBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.validateMaxLength
import com.google.android.material.snackbar.Snackbar

class RatingTutoring : Fragment() {
    private var _binding: FragmentRatingTutoringBinding? = null

    private val binding get() = _binding!!
    private lateinit var ratingTutoringVM: RatingTutoringVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRatingTutoringBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        ratingTutoringVM = ViewModelProvider(this, factory)[RatingTutoringVM::class.java]

        setupViews()
        setupListeners()
    }

    // TODO: Retrieve the tutor data and replace the dummy below
    private fun setupViews() {
        binding.apply {
            tbRatingTutor.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            tvTutorName.text = "Ilham Alfarizky"
            Glide.with(requireContext())
                .load("photoprofile")
                .into(ivTutorProfile)

            btnSubmitRating.isEnabled = false
            tilReview.isErrorEnabled = true
        }
    }

    private fun setupListeners() {
        binding.apply {
            etReview.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    tvCountWordReview.text = "${s?.length}/250"
                    showInputError(s?.validateMaxLength(250))
                    btnSubmitRating.isEnabled = !tilReview.isErrorEnabled && rbRating.rating != 0f
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            rbRating.setOnRatingBarChangeListener { _, rating, _ ->
                btnSubmitRating.isEnabled = !tilReview.isErrorEnabled && rating != 0f
            }

            btnSubmitRating.setOnClickListener {
                submitRating()
            }
        }
    }

    private fun submitRating() {
        val ratingNumber = binding.rbRating.rating.toInt()
        val reviewText = binding.etReview.text.toString()

        ratingTutoringVM.postRatingTutoring(ratingNumber, reviewText, 1)
            .observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ResultState.Loading -> {
                        binding.pbRatingTutoring.visibility = View.VISIBLE
                        binding.btnSubmitRating.text = null
                    }

                    is ResultState.Error -> {
                        binding.pbRatingTutoring.visibility = View.GONE
                        binding.btnSubmitRating.text =
                            resources.getString(R.string.btn_submit_rating)
                        setSnackBar(Event(result.error))
                    }

                    is ResultState.Success -> {
                        binding.pbRatingTutoring.visibility = View.GONE
                        binding.btnSubmitRating.text =
                            resources.getString(R.string.btn_submit_rating)

                        setSnackBar(Event("Berhasil memberikan rating"))

                    }
                }
            }
    }

    private fun showInputError(message: String?) {
        binding.apply {
            if (!message.isNullOrBlank()) {
                tilReview.isErrorEnabled = true
                tilReview.error = message
            } else {
                tilReview.isErrorEnabled = false
                tilReview.error = null
            }
            btnSubmitRating.isEnabled = !tilReview.isErrorEnabled && rbRating.rating != 0f
        }
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}
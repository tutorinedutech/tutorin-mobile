package com.dicoding.tutorinedutech.ui.learner.tutors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.tutorinedutech.data.response.ClassSessionsItem
import com.dicoding.tutorinedutech.databinding.FragmentTutorsLearnerBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.google.android.material.snackbar.Snackbar

class TutorsLearner : Fragment() {

    private var _binding: FragmentTutorsLearnerBinding? = null

    private val binding get() = _binding!!
    private lateinit var tutorsLearnerVM: TutorsLearnerVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorsLearnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        tutorsLearnerVM = ViewModelProvider(this, factory)[TutorsLearnerVM::class.java]

        val layoutLineaManager = LinearLayoutManager(requireContext())
        binding.rvListTutors.layoutManager = layoutLineaManager

        binding.apply {
            tutorsLearnerVM.getTutorsLearnerData().observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            pbTutorsLearner.visibility = View.VISIBLE
                        }

                        is ResultState.Error -> {
                            pbTutorsLearner.visibility = View.GONE
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            pbTutorsLearner.visibility = View.GONE

                            setTutorsLearnerData(result.data.data)
                        }
                    }
                }
            }
        }
    }

    private fun setTutorsLearnerData(data: List<ClassSessionsItem>) {
        // set recyclerview Tutors Data
        val adapter = TutorsLearnerAdapter()
        adapter.submitList(data)
        binding.rvListTutors.adapter = adapter

        adapter.setOnClickCallback(object : TutorsLearnerAdapter.OnItemClickCallback{
            override fun onItemClicked(session: ClassSessionsItem) {
                findNavController().navigate(TutorsLearnerDirections.actionTutorsLearnerToDetailTutoring(sessionId = session.id))
            }
        })
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}
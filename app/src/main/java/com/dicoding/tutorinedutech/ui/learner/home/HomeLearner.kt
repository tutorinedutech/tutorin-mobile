package com.dicoding.tutorinedutech.ui.learner.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.data.response.Data
import com.dicoding.tutorinedutech.databinding.FragmentHomeLearnerBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.utils.Event
import com.google.android.material.snackbar.Snackbar

class HomeLearner : Fragment() {

    private var _binding: FragmentHomeLearnerBinding? = null

    private val binding get() = _binding!!
    private lateinit var homeLearnerVM: HomeLearnerVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeLearnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        homeLearnerVM = ViewModelProvider(this, factory)[HomeLearnerVM::class.java]

        val layoutGridManager = GridLayoutManager(requireContext(), 2)
        binding.rvPopularTutors.layoutManager = layoutGridManager

        val layoutLineaManager = LinearLayoutManager(requireContext())
        binding.rvActivities.layoutManager = layoutLineaManager

        binding.apply {
            homeLearnerVM.getHomeData().observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            pbHomeLearner.visibility = View.VISIBLE
                            layoutHomeLearner.visibility = View.GONE
                        }

                        is ResultState.Error -> {
                            pbHomeLearner.visibility = View.GONE
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            pbHomeLearner.visibility = View.GONE
                            layoutHomeLearner.visibility = View.VISIBLE

                            searchBar.setOnClickListener {
                                findNavController().navigate(HomeLearnerDirections.actionHomeLearnerToSearchTutor())
                            }

                            setHomeData(result.data.data)
                        }
                    }
                }
            }
        }
    }

    private fun setHomeData(data: Data) {
        binding.tvHomeLearner.text =
            getString(R.string.greeting_learner, data.name)

        // set Learner Sessions
        binding.numberSession.text = getString(R.string.session_number, data.classDetails.size)

        // TODO: remove while page completed
        binding.numberSession.setOnClickListener {
            findNavController().navigate(HomeLearnerDirections.actionHomeLearnerToDetailTutor())
        }

        if (data.classDetails.isEmpty()) {
            binding.layoutSession.visibility = View.GONE

            binding.tvNoSession.visibility = View.VISIBLE
        } else {
            binding.layoutSession.visibility = View.VISIBLE

            val adapter = SessionAdapter()
            adapter.submitList(data.classDetails.take(5))
            binding.rvActivities.adapter = adapter
        }

        // see more session
        binding.tvMore.setOnClickListener {
            findNavController().navigate(HomeLearnerDirections.actionHomeLearnerToTutorsLearner())
        }

        // set Popular Tutor Data
        val adapter = PopularTutorAdapter()
        adapter.submitList(data.topFiveTutors)
        binding.rvPopularTutors.adapter = adapter
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}
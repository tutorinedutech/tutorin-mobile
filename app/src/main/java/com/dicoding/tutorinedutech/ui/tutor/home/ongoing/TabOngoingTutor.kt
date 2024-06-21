package com.dicoding.tutorinedutech.ui.tutor.home.ongoing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.data.db.tutor.Classes
import com.dicoding.tutorinedutech.data.db.tutor.ClassesAndClassDetail
import com.dicoding.tutorinedutech.databinding.FragmentTabOngoingTutorBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.ui.tutor.home.HomeTutorDirections
import com.dicoding.tutorinedutech.utils.Event
import com.google.android.material.snackbar.Snackbar


class TabOngoingTutor : Fragment() {
    private var _binding: FragmentTabOngoingTutorBinding? = null
    private val binding get() = _binding!!
    private lateinit var ongoingVM: TabOngoingVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabOngoingTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        ongoingVM = ViewModelProvider(this, factory)[TabOngoingVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClassListData()

        binding.apply {

        }
    }


    private fun setClassListData() {
        val adapter = ListOngoingAdapter(this)

        ongoingVM.getOngoingClassWithOneSession().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.rvOngoingTutor.adapter = adapter
        }
        adapter.setOnItemClickCallback(object : ListOngoingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ClassesAndClassDetail, view: View) {
                findNavController().navigate(HomeTutorDirections.actionHomeTutorToDetailTentoringTutor(data.classes.id, "ongoing"))
            }
        })
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }

}
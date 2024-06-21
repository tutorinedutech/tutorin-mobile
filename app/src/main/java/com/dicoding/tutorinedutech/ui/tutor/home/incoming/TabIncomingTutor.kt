package com.dicoding.tutorinedutech.ui.tutor.home.incoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.data.db.tutor.ClassesAndClassDetail
import com.dicoding.tutorinedutech.databinding.FragmentTabIncomingTutorBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.ui.tutor.home.HomeTutorDirections


class TabIncomingTutor : Fragment() {
    private var _binding: FragmentTabIncomingTutorBinding? = null
    private val binding get() = _binding!!
    private lateinit var incomingVM: TabIncomingVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabIncomingTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        incomingVM = ViewModelProvider(this, factory)[TabIncomingVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClassListData()
    }

    private fun setClassListData() {
        val adapter = ListIncomingAdapter(this)

        incomingVM.getIncomingClass().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.rvIncomingTutor.adapter = adapter
        }
        adapter.setOnItemClickCallback(object : ListIncomingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ClassesAndClassDetail, view: View) {
                findNavController().navigate(
                    HomeTutorDirections.actionHomeTutorToDetailTentoringTutor(
                        data.classes.id,
                        "incoming"
                    )
                )
            }
        })
    }

}
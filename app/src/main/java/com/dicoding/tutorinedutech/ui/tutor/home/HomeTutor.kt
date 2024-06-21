package com.dicoding.tutorinedutech.ui.tutor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentHomeTutorBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class HomeTutor : Fragment() {
    private var _binding: FragmentHomeTutorBinding? = null
    private val binding get() = _binding!!
    private var homePagerAdapter: HomePagerAdapter? = null
    private lateinit var homeVM: HomeVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTutorBinding.inflate(inflater, container, false)
        homePagerAdapter = activity?.let { HomePagerAdapter(it) }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        homeVM = ViewModelProvider(this, factory)[HomeVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM.fetchHomeData()

        binding.apply {
            vpHome.adapter = homePagerAdapter
            TabLayoutMediator(tabHome, vpHome) { tab, pos ->
                tab.text = resources.getString(TAB_TITLES[pos])
            }.attach()
            homeVM.getUserData().observe(viewLifecycleOwner) { tutor ->
                tvUserName.text = tutor?.name
                Glide.with(requireContext())
                    .load(tutor?.profilePicture)
                    .into(ivUserTutorProfile)
            }
            tbHomeTutor.setOnClickListener {
                findNavController().navigate(HomeTutorDirections.actionHomeTutorToProfileTutor())
            }
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_ongoing_tutor,
            R.string.tab_incoming_tutor
        )
    }

}
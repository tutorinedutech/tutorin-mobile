package com.dicoding.tutorinedutech.ui.tutor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentHomeTutorBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeTutor : Fragment() {
    private var _binding: FragmentHomeTutorBinding? = null
    private val binding get() = _binding!!
    private var homePagerAdapter: HomePagerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTutorBinding.inflate(inflater, container, false)
        homePagerAdapter = activity?.let { HomePagerAdapter(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vpHome.adapter = homePagerAdapter
            TabLayoutMediator(tabHome, vpHome) { tab, pos ->
                tab.text = resources.getString(TAB_TITLES[pos])
            }.attach()
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
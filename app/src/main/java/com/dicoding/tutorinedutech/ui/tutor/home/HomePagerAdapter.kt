package com.dicoding.tutorinedutech.ui.tutor.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.tutorinedutech.ui.tutor.home.incoming.TabIncomingTutor
import com.dicoding.tutorinedutech.ui.tutor.home.ongoing.TabOngoingTutor

class HomePagerAdapter(activity: FragmentActivity):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = TabOngoingTutor()
            1 -> fragment = TabIncomingTutor()
        }
        return fragment as Fragment

    }
}
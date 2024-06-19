package com.dicoding.tutorinedutech.ui.learner.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.tutorinedutech.data.response.ClassDetailsItem
import com.dicoding.tutorinedutech.databinding.ItemTodayActivityBinding
import com.dicoding.tutorinedutech.helper.Helper
import java.util.Date

class SessionAdapter : ListAdapter<ClassDetailsItem, SessionAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemTodayActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val session = getItem(position)
        holder.bind(session)
    }

    class MyViewHolder(val binding: ItemTodayActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(session: ClassDetailsItem) {
            binding.tvTimeSession.text = Helper.formatTime(session.timestamp)
            binding.tvTutorName.text = session.tutorName
            binding.tvTutorSpecialist.text = session.subject
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ClassDetailsItem>() {
            override fun areItemsTheSame(
                oldItem: ClassDetailsItem,
                newItem: ClassDetailsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ClassDetailsItem,
                newItem: ClassDetailsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
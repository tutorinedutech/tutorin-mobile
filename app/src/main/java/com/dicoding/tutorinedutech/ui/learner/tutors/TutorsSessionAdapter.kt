package com.dicoding.tutorinedutech.ui.learner.tutors


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.tutorinedutech.data.response.ClassDetailsItem
import com.dicoding.tutorinedutech.databinding.ItemListTimeSessionBinding
import com.dicoding.tutorinedutech.helper.Helper
import java.util.Date

class TutorsSessionAdapter :
    ListAdapter<ClassDetailsItem, TutorsSessionAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemListTimeSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val session = getItem(position)
        holder.bind(session)
    }

    class MyViewHolder(val binding: ItemListTimeSessionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(session: ClassDetailsItem) {
            binding.tvNumberSession.text = "Sesi ${session.session} "
            binding.tvListTimeSession.text = ": ${Helper.formatDateTime(session.timestamp)}"
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
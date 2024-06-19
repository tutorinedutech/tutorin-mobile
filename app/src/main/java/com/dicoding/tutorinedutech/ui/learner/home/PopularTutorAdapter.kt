package com.dicoding.tutorinedutech.ui.learner.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.tutorinedutech.data.response.TopFiveTutorsItem
import com.dicoding.tutorinedutech.databinding.ItemPopularTutorBinding

class PopularTutorAdapter() : ListAdapter<TopFiveTutorsItem, PopularTutorAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPopularTutorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val popularTutor = getItem(position)
        holder.bind(popularTutor, holder.itemView.context)
    }
    class MyViewHolder(val binding: ItemPopularTutorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(popularTutor: TopFiveTutorsItem, context: Context){
            Glide.with(context)
                .load(popularTutor.profilePicture)
                .into(binding.ivTutorProfile)
            binding.tvTutorName.text = popularTutor.name
            // TODO: update to tutor subject
            binding.tvTutorSpecialist.text = popularTutor.educationLevel
            binding.tvTutorRating.text = "${popularTutor.averageRating}/5"
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TopFiveTutorsItem>() {
            override fun areItemsTheSame(oldItem: TopFiveTutorsItem, newItem: TopFiveTutorsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TopFiveTutorsItem, newItem: TopFiveTutorsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
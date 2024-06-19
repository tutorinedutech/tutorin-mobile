package com.dicoding.tutorinedutech.ui.learner.tutors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.tutorinedutech.data.response.ClassSessionsItem
import com.dicoding.tutorinedutech.databinding.ItemTutorListBinding

class TutorsLearnerAdapter :
    ListAdapter<ClassSessionsItem, TutorsLearnerAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemTutorListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val session = getItem(position)
        holder.bind(session, holder.itemView.context)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(session)
        }
    }

    class MyViewHolder(val binding: ItemTutorListBinding) : RecyclerView.ViewHolder(binding.root) {

        private val tutorsSessionAdapter = TutorsSessionAdapter()

        fun bind(session: ClassSessionsItem, context: Context) {
            Glide.with(context)
                .load(session.profilePictureTutor)
                .into(binding.ivTutorProfile)
            binding.tvTutorName.text = session.nameTutor
            binding.tvTutorTopic.text = session.subject
            binding.tvLocation.text = session.learningMethod

            val hasToValidationStatus =
                session.classDetails.any { it.validationStatus == "not validated" }

            if (hasToValidationStatus) {
                binding.btnValidatePhoto.visibility = View.VISIBLE
            } else {
                binding.btnValidatePhoto.visibility = View.GONE
            }

            // TODO: navigate to validatePhoto fragment
            binding.btnValidatePhoto.setOnClickListener {}

            binding.rvListTutoringTime.layoutManager = LinearLayoutManager(binding.root.context)
            binding.rvListTutoringTime.adapter = tutorsSessionAdapter
            tutorsSessionAdapter.submitList(session.classDetails)
        }
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(session: ClassSessionsItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ClassSessionsItem>() {
            override fun areItemsTheSame(
                oldItem: ClassSessionsItem,
                newItem: ClassSessionsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ClassSessionsItem,
                newItem: ClassSessionsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package com.dicoding.tutorinedutech.ui.tutor.home.ongoing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.tutorinedutech.data.db.tutor.ClassesAndClassDetail
import com.dicoding.tutorinedutech.databinding.CardTutoringTutorBinding

class ListOngoingAdapter(private val fragment: Fragment) :
    ListAdapter<ClassesAndClassDetail, ListOngoingAdapter.UserViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class UserViewHolder(
        val binding: CardTutoringTutorBinding,
        private val fragment: Fragment,
        private val onItemClickCallback: OnItemClickCallback
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(classes: ClassesAndClassDetail) {
            binding.tvCardTutoringLearnerName.text = classes.classes.learner_name
            binding.tvStatusTutoring.text = "Akan berlangsung"
            binding.tvDatetimeCardTutoring.text =
                if (classes.classDetails.first().timestamp == null) "Waktu belum ditentukan" else classes.classDetails.first().timestamp
            binding.tvLocationCardTutoring.text =
                if (classes.classDetails.first().location == null) "Lokasi belum ditentukan" else classes.classDetails.first().location
            binding.btnDetailLearning.setOnClickListener {
                onItemClickCallback.onItemClicked(classes, it)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding =
            CardTutoringTutorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, fragment, onItemClickCallback)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ClassesAndClassDetail>() {
            override fun areItemsTheSame(
                oldItem: ClassesAndClassDetail,
                newItem: ClassesAndClassDetail
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ClassesAndClassDetail,
                newItem: ClassesAndClassDetail
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ClassesAndClassDetail, view: View)
    }
}
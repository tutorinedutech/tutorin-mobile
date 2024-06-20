package com.dicoding.tutorinedutech.ui.tutor.home.incoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.tutorinedutech.data.db.tutor.ClassIncoming
import com.dicoding.tutorinedutech.data.db.tutor.ClassesAndClassDetail
import com.dicoding.tutorinedutech.databinding.CardTutoringTutorBinding

class ListIncomingAdapter(private val fragment: Fragment) :
    ListAdapter<ClassIncoming, ListIncomingAdapter.ClassViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class ClassViewHolder(
        val binding: CardTutoringTutorBinding,
        private val fragment: Fragment,
        private val onItemClickCallback: OnItemClickCallback
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(classes: ClassIncoming) {
            binding.tvCardTutoringLearnerName.text = classes.learnerName
            binding.tvStatusTutoring.text = "Dalam penawaran"
//            binding.tvDatetimeCardTutoring.text =
//                if (classes.classDetails.first().timestamp == null) "Waktu belum ditentukan" else classes.classDetails.first().timestamp
//            binding.tvLocationCardTutoring.text =
//                if (classes.classDetails.first().location == null) "Lokasi belum ditentukan" else classes.classDetails.first().location
//            binding.btnDetailLearning.setOnClickListener {
//                onItemClickCallback.onItemClicked(classes, it)
//            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassViewHolder {
        val binding =
            CardTutoringTutorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding, fragment, onItemClickCallback)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classes = getItem(position)
        holder.bind(classes)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ClassIncoming>() {
            override fun areItemsTheSame(
                oldItem: ClassIncoming,
                newItem: ClassIncoming
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ClassIncoming,
                newItem: ClassIncoming
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ClassesAndClassDetail, view: View)
    }
}
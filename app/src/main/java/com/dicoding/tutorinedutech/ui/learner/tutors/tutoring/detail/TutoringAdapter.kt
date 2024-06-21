package com.dicoding.tutorinedutech.ui.learner.tutors.tutoring.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.data.response.ClassDetailsItem
import com.dicoding.tutorinedutech.databinding.ItemDetailTutoringBinding
import com.dicoding.tutorinedutech.helper.Helper

class TutoringAdapter :
    ListAdapter<ClassDetailsItem, TutoringAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemDetailTutoringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val detailLearning = getItem(position)
        holder.bind(detailLearning, holder.itemView.context)

        holder.binding.btnComplete.setOnClickListener {
            onItemClickCallback.onItemClicked(detailLearning.id)
        }
    }

    class MyViewHolder(val binding: ItemDetailTutoringBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailLearning: ClassDetailsItem, context: Context) {

            binding.tvSessionTitle.text =
                context.getString(R.string.session_number, detailLearning.session)

            if (detailLearning.location.isNullOrEmpty() && detailLearning.timestamp == null) {
                binding.btnComplete.visibility = View.VISIBLE

                binding.tvSessionLocation.text = "Lokasi belum diisi"
                binding.tvSessionDateTime.text = "Waktu belum diisi"
            } else {
                binding.btnComplete.visibility = View.GONE
                binding.tvSessionLocation.text = detailLearning.location
                binding.tvSessionDateTime.text = Helper.formatDateTime(detailLearning.timestamp)
            }

            // TODO: update background
            if (detailLearning.validationStatus == "Aprroved") {
                binding.cvDetailLearning.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.tertiary_yellow
                    )
                )
            }
        }
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(classDetailId: Int)
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
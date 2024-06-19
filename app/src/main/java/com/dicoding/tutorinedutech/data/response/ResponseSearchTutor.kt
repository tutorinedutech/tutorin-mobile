package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSearchTutor(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("teaching_approach")
	val teachingApproach: String,

	@field:SerializedName("id")
	val id: Int
)

package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseRatingTutoring(

	@field:SerializedName("data")
	val data: RatingTutoringData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class RatingTutoringData(

	@field:SerializedName("tutorId")
	val tutorId: Int,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("learnerId")
	val learnerId: Int,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("comment")
	val comment: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

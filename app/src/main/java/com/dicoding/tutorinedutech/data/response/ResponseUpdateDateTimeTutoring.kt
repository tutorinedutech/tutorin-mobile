package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseUpdateDateTimeTutoring(

	@field:SerializedName("data")
	val data: DateTimeLearningData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DateTimeLearningData(

	@field:SerializedName("session")
	val session: Int,

	@field:SerializedName("classSessionId")
	val classSessionId: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("proofImageLink")
	val proofImageLink: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("validationStatus")
	val validationStatus: String,

	@field:SerializedName("timestamp")
	val timestamp: String
)

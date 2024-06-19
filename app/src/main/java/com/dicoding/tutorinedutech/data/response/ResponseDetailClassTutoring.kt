package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailClassTutoring(

	@field:SerializedName("data")
	val data: DetailClassData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DetailClassData(

	@field:SerializedName("tutorId")
	val tutorId: Int,

	@field:SerializedName("sessions")
	val sessions: Int,

	@field:SerializedName("classDetails")
	val classDetails: List<ClassDetailsItem>,

	@field:SerializedName("profilePictureTutor")
	val profilePictureTutor: Any,

	@field:SerializedName("learnerId")
	val learnerId: Int,

	@field:SerializedName("subject")
	val subject: String,

	@field:SerializedName("nameTutor")
	val nameTutor: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("usernameTutor")
	val usernameTutor: String
)
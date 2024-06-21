package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailLearning(

	@field:SerializedName("data")
	val data: List<ClassSessionsItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DetailLearningItem(

	@field:SerializedName("tutor_id")
	val tutorId: Int? = null,

	@field:SerializedName("sessions")
	val sessions: Int? = null,

	@field:SerializedName("classDetails")
	val classDetails: List<ClassDetailsItem?>? = null,

	@field:SerializedName("usernameLearner")
	val usernameLearner: String? = null,

	@field:SerializedName("learner_id")
	val learnerId: Int? = null,

	@field:SerializedName("nameLearner")
	val nameLearner: String? = null,

	@field:SerializedName("subject")
	val subject: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseProfileTutor(

	@field:SerializedName("data")
	val data: DataProfile,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataProfile(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("educationLevel")
	val educationLevel: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("domicile")
	val domicile: String? = null,

	@field:SerializedName("languages")
	val languages: String? = null,

	@field:SerializedName("teachingApproach")
	val teachingApproach: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("accountNumber")
	val accountNumber: String? = null,

	@field:SerializedName("learningMethod")
	val learningMethod: String? = null,

	@field:SerializedName("ktp")
	val ktp: String? = null,

	@field:SerializedName("profilePicture")
	val profilePicture: String? = null,

	@field:SerializedName("cv")
	val cv: String? = null,


	@field:SerializedName("user")
	val user: UserProfile? = null,

	@field:SerializedName("reviews")
	val reviews: List<String?>? = null,

	@field:SerializedName("availabilities")
	val availabilities: List<Availabilities?>? = null,
)

data class Availabilities(
	@field:SerializedName("subject")
	val subject: String? = null,

	@field:SerializedName("day")
	val day: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class UserProfile(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

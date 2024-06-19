package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseProfileTutor(

	@field:SerializedName("data_profile")
	val data: DataProfile? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataProfile(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("languages")
	val languages: String? = null,

	@field:SerializedName("ktp")
	val ktp: String? = null,

	@field:SerializedName("availabilities")
	val availabilities: List<Any?>? = null,

	@field:SerializedName("learningMethod")
	val learningMethod: String? = null,

	@field:SerializedName("accountNumber")
	val accountNumber: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("profilePicture")
	val profilePicture: Any? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("cv")
	val cv: Any? = null,

	@field:SerializedName("reviews")
	val reviews: List<Any?>? = null,

	@field:SerializedName("educationLevel")
	val educationLevel: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("teachingApproach")
	val teachingApproach: String? = null,

	@field:SerializedName("domicile")
	val domicile: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("user_profile")
	val user: UserProfile? = null
)

data class UserProfile(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

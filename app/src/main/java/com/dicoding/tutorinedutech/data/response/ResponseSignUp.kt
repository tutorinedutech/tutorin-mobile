package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSignUp(

	@field:SerializedName("data")
	val data: DataSignUp? = null,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataSignUp(

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

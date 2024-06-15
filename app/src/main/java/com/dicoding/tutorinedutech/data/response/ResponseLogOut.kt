package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseLogOut(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

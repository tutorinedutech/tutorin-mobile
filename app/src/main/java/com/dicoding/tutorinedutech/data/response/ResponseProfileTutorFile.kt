package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseProfileTutorFile(

	@field:SerializedName("data")
	val data: DataFile,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataFile(
	@field:SerializedName("ktp")
	val ktp: String,

	@field:SerializedName("profilePicture")
	val profilePicture: String,

	@field:SerializedName("cv")
	val cv: String,
)


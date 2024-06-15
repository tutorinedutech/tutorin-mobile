package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSignIn(

    @field:SerializedName("data")
    val data: DataSignIn? = null,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataSignIn(

    @field:SerializedName("learnerId")
    val learnerId: Int? = null,

    @field:SerializedName("tutorId")
    val tutorId: Int? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("token")
    val token: String
)

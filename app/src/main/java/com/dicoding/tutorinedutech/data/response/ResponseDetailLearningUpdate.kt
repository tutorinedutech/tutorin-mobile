package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailLearningUpdate(

    @field:SerializedName("data")
    val data: ClassDetailsItem,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)
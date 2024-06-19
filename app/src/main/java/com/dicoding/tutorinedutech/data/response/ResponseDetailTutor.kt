package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailTutor(

    @field:SerializedName("data")
    val data: TutorData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class UserTutorDetail(

    @field:SerializedName("username")
    val username: String
)

data class TutorData(

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("languages")
    val languages: String,

    @field:SerializedName("availabilities")
    val availabilities: List<AvailabilitiesItem>,

    @field:SerializedName("learningMethod")
    val learningMethod: String,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("profilePicture")
    val profilePicture: String,

    @field:SerializedName("cv")
    val cv: String,

    @field:SerializedName("reviews")
    val reviews: List<Any>,

    @field:SerializedName("educationLevel")
    val educationLevel: String,

    @field:SerializedName("averageRating")
    val averageRating: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("teachingApproach")
    val teachingApproach: String,

    @field:SerializedName("domicile")
    val domicile: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("user")
    val user: UserTutorDetail
)

data class AvailabilitiesItem(

    @field:SerializedName("tutor_id")
    val tutorId: Int,

    @field:SerializedName("subject")
    val subject: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("day")
    val day: String
)

package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName

data class ResponseHomeTutor(

    @field:SerializedName("data")
    val data: DataHomeTutor? = null,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class ClassSessionsItem(

    @field:SerializedName("tutor_id")
    val tutorId: Int,

    @field:SerializedName("sessions")
    val sessions: Int? = null,

    @field:SerializedName("learner_id")
    val learnerId: Int? = null,

    @field:SerializedName("learner")
    val learner: LearnerDetailName? = null,

    @field:SerializedName("subject")
    val subject: String? = null,

    @field:SerializedName("id")
    val id: String
)

data class LearnerDetailName(
    @field:SerializedName("name")
    val name: String
)

data class User(

    @field:SerializedName("username")
    val username: String? = null
)

data class DataHomeTutor(

    @field:SerializedName("profilePicture")
    val profilePicture: String? = null,

    @field:SerializedName("classDetails")
    val classDetails: List<ClassDetailsItem?>? = null,

    @field:SerializedName("averageRating")
    val averageRating: Any? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("user")
    val user: User? = null,

    @field:SerializedName("classSessions")
    val classSessions: List<ClassSessionsItem?>? = null
)

data class ClassDetailsItem(

    @field:SerializedName("class_session_id")
    val classSessionId: String,

    @field:SerializedName("session")
    val session: Int? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("validation_status")
    val validationStatus: String? = null,

    @field:SerializedName("proof_image_link")
    val proofImageLink: String? = null,

    @field:SerializedName("timestamp")
    val timestamp: String? = null,

    @field:SerializedName("learner_name")
    val learnerName: String? = null,

    @field:SerializedName("subject")
    val subject: String? = null
)

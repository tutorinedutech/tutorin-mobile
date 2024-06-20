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
    val classSessions: List<ClassSessionsItem?>? = null,

    @field:SerializedName("purchases")
    val purchases: List<Purchase>? = null
)

data class Purchase(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("tutorId")
    val tutorId: Int,

    @field:SerializedName("learnerId")
    val learnerId: Int,

    @field:SerializedName("subject")
    val subject: String,

    @field:SerializedName("days")
    val days: List<String?>? = null,

    @field:SerializedName("times")
    val times: List<String?>? = null,

    @field:SerializedName("learning_method")
    val learningMethod: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("learner")
    val learner: LearnerData,
)

data class LearnerData(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("education_level")
    val educationLevel: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("domicile")
    val domicile: String? = null,

    @field:SerializedName("phone_number")
    val phoneNumber: String? = null,
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

package com.dicoding.tutorinedutech.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ResponseHomeLearner(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class User(

    @field:SerializedName("username")
    val username: String
)

data class ClassSessionsItem(

    @field:SerializedName("tutor_id")
    val tutorId: Int,

    @field:SerializedName("sessions")
    val sessions: Int,

    @field:SerializedName("classDetails")
    val classDetails: List<ClassDetailsItem>,

    @field:SerializedName("profilePictureTutor")
    val profilePictureTutor: String,

    @field:SerializedName("learner_id")
    val learnerId: Int,

    @field:SerializedName("subject")
    val subject: String,

    @field:SerializedName("nameTutor")
    val nameTutor: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("usernameTutor")
    val usernameTutor: String,

    @field:SerializedName("learningMethod")
    val learningMethod: String,

    @field:SerializedName("learner")
    val learner: LearnerDetailName? = null,

)

data class ClassDetailsItem(

    @field:SerializedName("class_session_id")
    val classSessionId: String,

    @field:SerializedName("session")
    val session: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("timestamp")
    val timestamp: Date,

    @field:SerializedName("validation_status")
    val validationStatus: String,

    @field:SerializedName("proof_image_link")
    val proofImageLink: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("tutorName")
    val tutorName: String,

    @field:SerializedName("subject")
    val subject: String,

    @field:SerializedName("learner_id")
    val learnerId: Int? = null,

    @field:SerializedName("learner")
    val learner: LearnerDetailName? = null,

    @field:SerializedName("learner_name")
    val learnerName: String? = null,
)

data class TopFiveTutorsItem(

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("education_level")
    val educationLevel: String,

    @field:SerializedName("teaching_approach")
    val teachingApproach: String,

    @field:SerializedName("profile_picture")
    val profilePicture: String,

    @field:SerializedName("average_rating")
    val averageRating: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("user")
    val user: User
)

data class Data(

    @field:SerializedName("classDetails")
    val classDetails: List<ClassDetailsItem>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("topFiveTutors")
    val topFiveTutors: List<TopFiveTutorsItem>,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("classSessions")
    val classSessions: List<ClassSessionsItem>
)

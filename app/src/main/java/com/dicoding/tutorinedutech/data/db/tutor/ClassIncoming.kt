package com.dicoding.tutorinedutech.data.db.tutor

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.tutorinedutech.data.response.LearnerData
import com.google.gson.annotations.SerializedName

@Entity(tableName = "class_incoming")
data class ClassIncoming(
    @PrimaryKey
    val id: Int,

    @field:SerializedName("tutorId")
    val tutorId: Int,

    @field:SerializedName("learnerId")
    val learnerId: Int,

    @field:SerializedName("subject")
    val subject: String,

    @field:SerializedName("days")
    val days: String? = null,

    @field:SerializedName("times")
    val times: String? = null,

    @field:SerializedName("learning_method")
    val learningMethod: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("learner_name")
    val learnerName: String? = null,

    @field:SerializedName("learner_education_level")
    val learnerEducationLevel: String? = null,

    @field:SerializedName("learner_gender")
    val learnerGender: String? = null,

    @field:SerializedName("learner_domicile")
    val learnerDomicile: String? = null,

    @field:SerializedName("learner_phone_number")
    val learnerPhoneNumber: String? = null,

    @field:SerializedName("session")
    val session: Int,
)
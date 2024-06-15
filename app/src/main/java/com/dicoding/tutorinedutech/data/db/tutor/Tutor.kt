package com.dicoding.tutorinedutech.data.db.tutor

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_tutor")
data class Tutor(
    @PrimaryKey val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String? = null,

    @field:SerializedName("educationLevel")
    val educationLevel: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("domicile")
    val domicile: String? = null,

    @field:SerializedName("languages")
    val languages: String? = null,

    @field:SerializedName("teachingCriteria")
    val teachingApproach: String? = null,

    @field:SerializedName("ktp")
    val ktp: String? = null,

    @field:SerializedName("accountNumber")
    val accountNumber: String? = null,

    @field:SerializedName("learningMethod")
    val learningMethod: String? = null,

    @field:SerializedName("profilePicture")
    val profilePicture: String? = null,

    @field:SerializedName("cv")
    val cv: String? = null,
)
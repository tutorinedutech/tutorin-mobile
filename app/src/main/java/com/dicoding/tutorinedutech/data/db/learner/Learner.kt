package com.dicoding.tutorinedutech.data.db.learner

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_learner")
data class Learner (
    @PrimaryKey val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

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
)
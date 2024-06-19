package com.dicoding.tutorinedutech.data.db.tutor

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(tableName = "class_detail")
data class ClassDetail(
	@PrimaryKey
	val id: Int,

	val classSessionId: String,

	@field:SerializedName("session")
	val session: Int? = null,

	@field:SerializedName("subject")
	val subject: String? = null,

	@field:SerializedName("learnerName")
	val learnerName: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("validation_status")
	val validationStatus: String? = null,

	@field:SerializedName("proof_image_link")
	val proofImageLink: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)


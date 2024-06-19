package com.dicoding.tutorinedutech.data.db.tutor

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "classes")
data class Classes(
    @PrimaryKey
    val id: String,

    @field:SerializedName("tutor_id")
    val tutorId: Int,

    @field:SerializedName("sessions")
    val sessions: Int? = null,

    @field:SerializedName("learner_id")
    val learnerId: Int? = null,

    @field:SerializedName("subject")
    val subject: String? = null,

    @field:SerializedName("learner")
    val learner_name: String? = null,
    )

data class ClassesAndClassDetail(
    @Embedded val classes: Classes,
    @Relation(
        parentColumn = "id",
        entityColumn = "classSessionId"
    )
    val classDetails: List<ClassDetail>
)

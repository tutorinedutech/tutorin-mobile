package com.dicoding.tutorinedutech.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ValidationPhotoData(
    var id: Int,
    var nameTutor: String,
    var usernameTutor: String,
    var subject: String,
    var proofImageLink: String,
    var session: Int,
    var location: String,
    var timestamp: Date
) : Parcelable
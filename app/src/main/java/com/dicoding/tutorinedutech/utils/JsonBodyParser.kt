package com.dicoding.tutorinedutech.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

fun createJsonRequestBody(vararg params: Pair<String, String>) =
    JSONObject(mapOf(*params)).toString()
        .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
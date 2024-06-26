package com.dicoding.tutorinedutech.data.retrofit

import android.content.Context
import android.util.Log
import com.dicoding.tutorinedutech.BuildConfig
import com.dicoding.tutorinedutech.utils.PrefLearner
import com.dicoding.tutorinedutech.utils.PrefTutor
import com.dicoding.tutorinedutech.utils.UserType
import com.dicoding.tutorinedutech.utils.UserTypeManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        var baseURL = BuildConfig.BASE_API_URL
        fun getApiService(context: Context, prefTutor: PrefTutor?, prefLearner: PrefLearner?): ApiService {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val token = runBlocking {
                    when (UserTypeManager.getUserType(context)) {
                        UserType.TUTOR -> {
                            prefTutor?.getUserToken()?.first()
                        }
                        UserType.LEARNER -> {
                            prefLearner?.getUserToken()?.first()
                        }
                        else -> null
                    }
                }
                Log.d("Token APIs", token.toString())
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
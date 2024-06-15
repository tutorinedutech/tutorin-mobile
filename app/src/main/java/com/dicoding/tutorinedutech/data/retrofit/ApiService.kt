package com.dicoding.tutorinedutech.data.retrofit

import com.dicoding.tutorinedutech.data.response.ResponseSignIn
import com.dicoding.tutorinedutech.data.response.ResponseSignUp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("signuptutors")
    fun registerTutor(
        @Part("email") email: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("name") name: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("educationLevel") educationLevel: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("domicile") domicile: RequestBody,
        @Part("languages") languages: RequestBody,
        @Part("teachingApproach") teachingApproach: RequestBody,
        @Part ktp: MultipartBody.Part,
        @Part("accountNumber") accountNumber: RequestBody,
        @Part("learningMethod") learningMethod: RequestBody,
        @Part profilePicture: MultipartBody.Part? = null,
        @Part cv: MultipartBody.Part? = null,
    ): Call<ResponseSignUp>

    @FormUrlEncoded
    @POST("signuplearners")
    fun registerLearner(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("educationLevel") educationLevel: String,
        @Field("gender") gender: String,
        @Field("domicile") domicile: String,
    ): Call<ResponseSignUp>

    @FormUrlEncoded
    @POST("signin")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseSignIn>

    @POST("signout")
    fun logout()
}
package com.dicoding.tutorinedutech.data.retrofit

import com.dicoding.tutorinedutech.data.response.ResponseBase
import com.dicoding.tutorinedutech.data.response.ResponseDetailLearning
import com.dicoding.tutorinedutech.data.response.ResponseDetailLearningUpdate
import com.dicoding.tutorinedutech.data.response.ResponseHomeTutor
import com.dicoding.tutorinedutech.data.response.ResponseProfileTutor
import com.dicoding.tutorinedutech.data.response.ResponseSignIn
import com.dicoding.tutorinedutech.data.response.ResponseSignUp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("tutors/home")
    fun getHomeTutor(): Call<ResponseHomeTutor>

    @GET("tutors/my-profile")
    fun getProfileTutor(): Call<ResponseProfileTutor>

    @FormUrlEncoded
    @PUT("tutors/my-profile")
    fun updateTutor(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("educationLevel") educationLevel: String,
        @Field("gender") gender: String,
        @Field("domicile") domicile: String,
        @Field("languages") languages: String,
        @Field("teachingApproach") teachingApproach: String,
        @Field("accountNumber") accountNumber: String,
        @Field("learningMethod") learningMethod: String,
    ): Call<ResponseProfileTutor>

    @Multipart
    @PUT("tutors/my-profile")
    fun updateTutorFile(
        @Part profilePicture: MultipartBody.Part?,
        @Part cv: MultipartBody.Part?,
    ): Call<ResponseProfileTutor>

    @DELETE("tutors/my-profile")
    fun deleteUserFile(@Query("file") file: String?): Call<ResponseBase>

    @GET("class-details/detail-learning")
    fun getDetailLearningTutor(): Call<ResponseDetailLearning>

    @Multipart
    @PUT("class-details/detail-learning/{classDetailId}")
    fun updateDetailLearningTutor(
        @Path("classDetailId") id: RequestBody,
        @Part location: MultipartBody.Part?,
        @Part proofImage: MultipartBody.Part?,
    ): Call<ResponseDetailLearningUpdate>
}
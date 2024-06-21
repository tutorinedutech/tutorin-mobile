package com.dicoding.tutorinedutech.data.retrofit

import com.dicoding.tutorinedutech.data.response.ResponseDetailClassTutoring
import com.dicoding.tutorinedutech.data.response.ResponseDetailLearning
import com.dicoding.tutorinedutech.data.response.ResponseDetailTutor
import com.dicoding.tutorinedutech.data.response.ResponseHomeLearner
import com.dicoding.tutorinedutech.data.response.ResponseRatingTutoring
import com.dicoding.tutorinedutech.data.response.ResponseSearchTutor
import com.dicoding.tutorinedutech.data.response.ResponseBase
import com.dicoding.tutorinedutech.data.response.ResponseDetailLearningUpdate
import com.dicoding.tutorinedutech.data.response.ResponseHomeTutor
import com.dicoding.tutorinedutech.data.response.ResponseProfileTutor
import com.dicoding.tutorinedutech.data.response.ResponseProfileTutorFile
import com.dicoding.tutorinedutech.data.response.ResponseSignIn
import com.dicoding.tutorinedutech.data.response.ResponseSignUp
import com.dicoding.tutorinedutech.data.response.ResponseUpdateDateTimeTutoring
import com.dicoding.tutorinedutech.data.response.ResponseUpdateDetailClass
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.Date
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

    @GET("learners/home")
    fun getLearnerHome(
    ): Call<ResponseHomeLearner>

    @GET("class-details/detail-tutoring")
    fun getDetailLearning(
    ): Call<ResponseDetailLearning>

    @FormUrlEncoded
    @POST("tutors")
    fun searchTutor(
        @Field("educationLevel") educationLevel: String,
        @Field("gender") gender: String,
        @Field("domicile") domicile: String,
        @Field("language") language: String,
        @Field("subject") subject: String,
        @Field("learningMethod") learningMethod: String,
        @Field("day") day: Array<String>,
        @Field("time") time: Array<String>
    ): Call<ResponseSearchTutor>

    @GET("class-details/{session_id}")
    fun getDetailClassTutoring(
        @Path("session_id") sessionId: String
    ): Call<ResponseDetailClassTutoring>

    @PUT("class-details/detail-tutoring/{classDetailsId}")
    fun updateDetailClass(
        @Path("classDetailsId") classDetailsId: Int
    ): Call<ResponseUpdateDetailClass>

    @GET("tutors/{tutorId}")
    fun getDetailTutorById(
        @Path("tutorId") tutorId: Int
    ): Call<ResponseDetailTutor>

    @FormUrlEncoded
    @POST("tutors/{tutorId}/reviews")
    fun postRatingTutoring(
        @Field("rating") rating: Int,
        @Field("comment") comment: String,
        @Path("tutorId") tutorId: Int
    ): Call<ResponseRatingTutoring>

    @FormUrlEncoded
    @PUT("class-details/detail-tutoring/{classDetailId}/schedule")
    fun updateDateTimeTutoring(
        @Field("timestamp") timestamp: Date,
        @Field("location") location: String,
        @Path("classDetailId") classDetailId: Int
    ): Call<ResponseUpdateDateTimeTutoring>

    @GET("tutors/home")
    fun getHomeTutor(): Call<ResponseHomeTutor>

    @GET("tutors/my-profile")
    fun getProfileTutor(): Call<ResponseProfileTutor>

    @Headers(value = ["Content-Type: application/json"])
    @PUT("tutors/my-profile")
    fun updateTutor(
        @Body updateBody: RequestBody,
    ): Call<ResponseProfileTutor>

    @Multipart
    @PUT("tutors/my-profile")
    fun updateTutorCv(
        @Part cv: MultipartBody.Part?,
    ): Call<ResponseProfileTutorFile>

    @Multipart
    @PUT("tutors/my-profile")
    fun updateTutorPicture(
        @Part profilePicture: MultipartBody.Part?,
    ): Call<ResponseProfileTutorFile>

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
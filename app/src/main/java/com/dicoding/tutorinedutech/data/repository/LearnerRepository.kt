package com.dicoding.tutorinedutech.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.tutorinedutech.data.response.ResponseDetailClassTutoring
import com.dicoding.tutorinedutech.data.response.ResponseDetailLearning
import com.dicoding.tutorinedutech.data.response.ResponseDetailTutor
import com.dicoding.tutorinedutech.data.response.ResponseHomeLearner
import com.dicoding.tutorinedutech.data.response.ResponseRatingTutoring
import com.dicoding.tutorinedutech.data.response.ResponseSearchTutor
import com.dicoding.tutorinedutech.data.response.ResponseUpdateDateTimeTutoring
import com.dicoding.tutorinedutech.data.response.ResponseUpdateDetailClass
import com.dicoding.tutorinedutech.data.retrofit.ApiService
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.utils.PrefLearner
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class LearnerRepository private constructor(
    private val apiService: ApiService,
    private val prefLearner: PrefLearner,
) {

    fun learnerHome(): LiveData<ResultState<ResponseHomeLearner>> {
        val result = MediatorLiveData<ResultState<ResponseHomeLearner>>()
        result.value = ResultState.Loading
        val client = apiService.getLearnerHome()

        client.enqueue(object : Callback<ResponseHomeLearner> {
            override fun onResponse(
                call: Call<ResponseHomeLearner>,
                res: Response<ResponseHomeLearner>
            ) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ResponseHomeLearner::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseHomeLearner>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }

        })
        return result
    }

    fun detailTutoring(): LiveData<ResultState<ResponseDetailLearning>> {
        val result = MediatorLiveData<ResultState<ResponseDetailLearning>>()
        result.value = ResultState.Loading
        val client = apiService.getDetailLearning()

        client.enqueue(object : Callback<ResponseDetailLearning> {
            override fun onResponse(
                call: Call<ResponseDetailLearning>,
                res: Response<ResponseDetailLearning>
            ) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ResponseDetailLearning::class.java)
                    val errorMessage = errorBody.message
                    result.value = errorMessage?.let { ResultState.Error(it) }
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseDetailLearning>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }

        })
        return result
    }

    fun detailClassTutoring(sessionId: String): LiveData<ResultState<ResponseDetailClassTutoring>> {
        val result = MediatorLiveData<ResultState<ResponseDetailClassTutoring>>()
        result.value = ResultState.Loading
        val client = apiService.getDetailClassTutoring(sessionId)

        client.enqueue(object : Callback<ResponseDetailClassTutoring> {
            override fun onResponse(
                call: Call<ResponseDetailClassTutoring>,
                res: Response<ResponseDetailClassTutoring>
            ) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ResponseDetailClassTutoring::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseDetailClassTutoring>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }

        })
        return result
    }

    fun detailClassValidation(classDetailId: Int): LiveData<ResultState<ResponseUpdateDetailClass>> {
        val result = MediatorLiveData<ResultState<ResponseUpdateDetailClass>>()
        result.value = ResultState.Loading
        val client = apiService.updateDetailClass(classDetailId)

        client.enqueue(object : Callback<ResponseUpdateDetailClass> {
            override fun onResponse(
                call: Call<ResponseUpdateDetailClass>,
                res: Response<ResponseUpdateDetailClass>
            ) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ResponseUpdateDetailClass::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseUpdateDetailClass>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }

        })
        return result
    }

    fun detailTutorById(tutorId: Int): LiveData<ResultState<ResponseDetailTutor>> {
        val result = MediatorLiveData<ResultState<ResponseDetailTutor>>()
        result.value = ResultState.Loading
        val client = apiService.getDetailTutorById(tutorId)

        client.enqueue(object : Callback<ResponseDetailTutor> {
            override fun onResponse(
                call: Call<ResponseDetailTutor>,
                res: Response<ResponseDetailTutor>
            ) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ResponseDetailTutor::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseDetailTutor>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }

        })
        return result
    }

    fun searchTutor(
        educationLevel: String,
        gender: String,
        domicile: String,
        language: String,
        learningMethod: String,
        day: Array<String>,
        time: Array<String>,
        subject: String
    ): LiveData<ResultState<ResponseSearchTutor>> {
        val result = MediatorLiveData<ResultState<ResponseSearchTutor>>()
        result.value = ResultState.Loading

        val client = apiService.searchTutor(
            educationLevel = educationLevel,
            gender = gender,
            domicile = domicile,
            language = language,
            learningMethod = learningMethod,
            day = day,
            time = time,
            subject = subject
        )

        client.enqueue(object : Callback<ResponseSearchTutor> {
            override fun onResponse(
                call: Call<ResponseSearchTutor>,
                res: Response<ResponseSearchTutor>
            ) {
                if (res.body()?.status != "success" && res.code() != 201) {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ResponseSearchTutor::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseSearchTutor>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }
        })
        return result
    }

    fun writeReview(
        rating: Int,
        comment: String,
        tutorId: Int
    ): LiveData<ResultState<ResponseRatingTutoring>> {
        val result = MediatorLiveData<ResultState<ResponseRatingTutoring>>()
        result.value = ResultState.Loading

        val client = apiService.postRatingTutoring(
            rating = rating,
            comment = comment,
            tutorId = tutorId
        )

        client.enqueue(object : Callback<ResponseRatingTutoring> {
            override fun onResponse(
                call: Call<ResponseRatingTutoring>,
                res: Response<ResponseRatingTutoring>
            ) {
                if (res.body()?.status != "success" && res.code() != 201) {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ResponseRatingTutoring::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseRatingTutoring>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }
        })
        return result
    }

    fun detailDateTimeClassTutoring(
        timestamp: Date,
        location: String,
        classDetailId: Int
    ): LiveData<ResultState<ResponseUpdateDateTimeTutoring>> {
        val result = MediatorLiveData<ResultState<ResponseUpdateDateTimeTutoring>>()
        result.value = ResultState.Loading
        val client = apiService.updateDateTimeTutoring(timestamp, location, classDetailId)

        client.enqueue(object : Callback<ResponseUpdateDateTimeTutoring> {
            override fun onResponse(
                call: Call<ResponseUpdateDateTimeTutoring>,
                res: Response<ResponseUpdateDateTimeTutoring>
            ) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ResponseUpdateDateTimeTutoring::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseUpdateDateTimeTutoring>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }

        })
        return result
    }

    companion object {
        @Volatile
        private var instance: LearnerRepository? = null
        fun getInstance(
            apiService: ApiService,
            prefLearner: PrefLearner,
        ): LearnerRepository =
            instance ?: synchronized(this) {
                instance ?: LearnerRepository(apiService, prefLearner)
            }.also { instance = it }
    }
}
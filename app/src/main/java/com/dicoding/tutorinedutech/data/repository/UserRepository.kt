package com.dicoding.tutorinedutech.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.tutorinedutech.data.db.learner.Learner
import com.dicoding.tutorinedutech.data.db.learner.LearnerDatabase
import com.dicoding.tutorinedutech.data.db.tutor.Tutor
import com.dicoding.tutorinedutech.data.db.tutor.TutorDatabase
import com.dicoding.tutorinedutech.data.db.tutor.TutorDetail
import com.dicoding.tutorinedutech.data.response.ResponseSignIn
import com.dicoding.tutorinedutech.data.response.ResponseSignUp
import com.dicoding.tutorinedutech.data.retrofit.ApiService
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.utils.AppExecutor
import com.dicoding.tutorinedutech.utils.PrefLearner
import com.dicoding.tutorinedutech.utils.PrefMain
import com.dicoding.tutorinedutech.utils.PrefTutor
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val apiService: ApiService,
    private val prefLearner: PrefLearner,
    private val prefTutor: PrefTutor,
    private val prefMain: PrefMain,
    private val appExecutor: AppExecutor,
    private val learnerDatabase: LearnerDatabase,
    private val tutorDatabase: TutorDatabase
) {

    fun getOnboardingStatus() = prefMain.getOnboardingStatus()

    suspend fun setOnboardingStatus(status: Boolean = false) = prefMain.setOnboardingStatus(status)

    fun getLearnerToken() = prefLearner.getUserToken()
    fun getTutorToken() = prefTutor.getUserToken()

    suspend fun saveLearnerToken(token: String) = prefLearner.setUserToken(token)
    suspend fun saveTutorToken(token: String) = prefTutor.setUserToken(token)

    suspend fun logout() {
        prefTutor.clearUserToken()
        prefLearner.clearUserToken()
    }

    fun getLearner() = learnerDatabase.learnerDao().getUser()

    fun getTutor() = tutorDatabase.tutorDao().getUser()

    fun saveCreatedLearnerData(learner: Learner) =
        appExecutor.diskIO.execute { learnerDatabase.learnerDao().setCreateUser(learner) }

    fun getCreatedLeaernerData() = learnerDatabase.learnerDao().getCreateUser()

    fun updateCreatedLearnerData(learner: Learner) = appExecutor.diskIO.execute {
        learnerDatabase.learnerDao().updateCreateUser(learner)
    }

    fun saveCreatedTutorData(tutor: Tutor) =
        appExecutor.diskIO.execute { tutorDatabase.tutorDao().setCreateUser(tutor) }

    fun getCreatedTutorData() = tutorDatabase.tutorDao().getCreateUser()

    fun updateCreatedTutorData(tutor: TutorDetail) =
        appExecutor.diskIO.execute { tutorDatabase.tutorDao().updateCreateUser(tutor) }


    fun login(username: String, password: String): LiveData<ResultState<ResponseSignIn>> {
        val result = MediatorLiveData<ResultState<ResponseSignIn>>()
        result.value = ResultState.Loading
        val client = apiService.login(username, password)

        client.enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(call: Call<ResponseSignIn>, res: Response<ResponseSignIn>) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ResponseSignIn::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                    val userData = res.body()?.data
                    if (userData?.tutorId != null) {
                        runBlocking {
                            saveTutorToken(userData.token)
                        }
                        val userModel = Tutor(
                            id = userData.tutorId,
                            userId = userData.id,
                            username = userData.username
                        )
                        appExecutor.diskIO.execute { tutorDatabase.tutorDao().setUser(userModel) }
                    } else if (userData?.learnerId != null) {
                        runBlocking {
                            saveLearnerToken(userData.token)
                        }
                        val userModel = Learner(
                            id = userData.learnerId,
                            userId = userData.id,
                            username = userData.username
                        )
                        learnerDatabase.learnerDao().setUser(userModel)
                    } else {
                        result.value = ResultState.Error("Invalid token data")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }

        })
        return result
    }

    fun registerLearner(
        email: String,
        username: String,
        password: String,
        name: String,
        phoneNumber: String,
        educationLevel: String,
        gender: String,
        domicile: String
    ): LiveData<ResultState<ResponseSignUp>> {
        val result = MediatorLiveData<ResultState<ResponseSignUp>>()
        result.value = ResultState.Loading

        val client = apiService.registerLearner(
            email = email,
            username = username,
            password = password,
            name = name,
            phoneNumber = phoneNumber,
            educationLevel = educationLevel,
            gender = gender,
            domicile = domicile
        )

        client.enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse(call: Call<ResponseSignUp>, res: Response<ResponseSignUp>) {
                if (res.body()?.status != "success" && res.code() != 201) {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ResponseSignUp::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }
        })
        return result

    }


    fun registerTutor(
        email: RequestBody,
        username: RequestBody,
        password: RequestBody,
        name: RequestBody,
        phoneNumber: RequestBody,
        educationLevel: RequestBody,
        gender: RequestBody,
        domicile: RequestBody,
        accountNumber: RequestBody,
        languages: RequestBody,
        teachingApproach: RequestBody,
        learningMethod: RequestBody,
        ktp: MultipartBody.Part
    ): LiveData<ResultState<ResponseSignUp>> {
        val result = MediatorLiveData<ResultState<ResponseSignUp>>()
        result.value = ResultState.Loading

        val client = apiService.registerTutor(
            email = email,
            username = username,
            password = password,
            name = name,
            phoneNumber = phoneNumber,
            educationLevel = educationLevel,
            gender = gender,
            domicile = domicile,
            accountNumber = accountNumber,
            languages = languages,
            ktp = ktp,
            teachingApproach = teachingApproach,
            learningMethod = learningMethod,
        )

        client.enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse(call: Call<ResponseSignUp>, res: Response<ResponseSignUp>) {
                if (res.body()?.status != "success" && res.code() != 201) {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ResponseSignUp::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }
        })
        return result

    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            prefLearner: PrefLearner,
            prefTutor: PrefTutor,
            prefMain: PrefMain,
            appExecutor: AppExecutor,
            learnerDatabase: LearnerDatabase,
            tutorDatabase: TutorDatabase
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(
                apiService,
                prefLearner,
                prefTutor,
                prefMain,
                appExecutor,
                learnerDatabase,
                tutorDatabase
            )
        }.also { instance = it }
    }

}
package com.dicoding.tutorinedutech.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.tutorinedutech.data.db.tutor.ClassDetail
import com.dicoding.tutorinedutech.data.db.tutor.ClassIncoming
import com.dicoding.tutorinedutech.data.db.tutor.Classes
import com.dicoding.tutorinedutech.data.db.tutor.TutorDatabase
import com.dicoding.tutorinedutech.data.db.tutor.TutorUpdateHome
import com.dicoding.tutorinedutech.data.response.ResponseHomeTutor
import com.dicoding.tutorinedutech.data.retrofit.ApiService
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.utils.AppExecutor
import com.dicoding.tutorinedutech.utils.PrefMain
import com.dicoding.tutorinedutech.utils.PrefTutor
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TutoringRepository private constructor(
    private val apiService: ApiService,
    private val prefTutor: PrefTutor,
    private val prefMain: PrefMain,
    private val appExecutor: AppExecutor,
    private val tutorDatabase: TutorDatabase
) {

    fun getAllClass() = tutorDatabase.classesDao().getAllClass()
    fun getAllClassWOneSession() = tutorDatabase.classesDao().getAllClassWDetail()
    fun getOneClassWOneSession(id:String) = tutorDatabase.classesDao().getOneClassWDetail(id)
    fun getIncomingClass() = tutorDatabase.classIncomingDao().getAllClassIncoming()

    fun getHomeData(): LiveData<ResultState<ResponseHomeTutor>> {
        val result = MediatorLiveData<ResultState<ResponseHomeTutor>>()
        result.value = ResultState.Loading
        val client = apiService.getHomeTutor()

        client.enqueue(object : Callback<ResponseHomeTutor> {
            override fun onResponse(
                call: Call<ResponseHomeTutor>,
                res: Response<ResponseHomeTutor>
            ) {
                if (res.body()?.status != "success") {
                    val jsonInString = res.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ResponseHomeTutor::class.java)
                    val errorMessage = errorBody.message
                    result.value = ResultState.Error(errorMessage)
                } else {
                    val homeData = res.body()?.data
                    val classData = homeData?.classSessions
                    val classList = ArrayList<Classes>()
                    val classDetailData = homeData?.classDetails
                    val classDetailList = ArrayList<ClassDetail>()
                    val classIncomingData = homeData?.purchases
                    val classIncomingList = ArrayList<ClassIncoming>()

                    val tutorData = TutorUpdateHome(
                        username = homeData?.user?.username,
                        id = homeData?.id,
                        userId = homeData?.userId,
                        name = homeData?.name,
                        profilePicture = homeData?.profilePicture,
                    )
                    appExecutor.diskIO.execute {
                        tutorDatabase.tutorDao().updateHomeUser(tutorData)
                        classData?.forEach { classes ->
                            val oneClass = Classes(
                                id = classes?.id!!,
                                tutorId = classes.tutorId,
                                learnerId = classes.learnerId,
                                learner_name = classes.learner?.name,
                                subject = classes.subject,
                                sessions = classes.sessions
                            )
                            classList.add(oneClass)
                        }
                        tutorDatabase.classesDao().setClass(classList)
                        classDetailData?.forEach { classDetail ->
                            val oneClass = ClassDetail(
                                id = classDetail?.id!!,
                                classSessionId = classDetail.classSessionId,
                                session = classDetail.session,
                                subject = classDetail.subject,
                                validationStatus = classDetail.validationStatus,
                                proofImageLink = classDetail.proofImageLink,
                                timestamp = classDetail.timestamp,
                                learnerName = classDetail.learnerName,
                                location = classDetail.location
                            )
                            classDetailList.add(oneClass)
                        }
                        tutorDatabase.classDetailDao().setClassDetail(classDetailList)
                        classIncomingData?.forEach { classIncoming ->
                            val maxDay = classIncoming.days?.size
                            val maxTime = classIncoming.times?.size
                            val oneClass = ClassIncoming(
                                tutorId = classIncoming.tutorId,
                                learnerName = classIncoming.learner.name,
                                learningMethod = classIncoming.learningMethod,
                                id = classIncoming.id,
                                session = if (maxDay!! >= maxTime!!) maxDay else if (maxTime >= maxDay) maxTime else 0,
                                learnerId = classIncoming.learnerId,
                                subject = classIncoming.subject,
                                learnerEducationLevel = classIncoming.learner.educationLevel,
                                learnerPhoneNumber = classIncoming.learner.phoneNumber,
                                learnerDomicile = classIncoming.learner.domicile,
                                days = classIncoming.days.joinToString(separator = ","),
                                learnerGender = classIncoming.learner.gender,
                                times = classIncoming.times.joinToString(separator = ","),
                                status = classIncoming.status
                            )
                            classIncomingList.add(oneClass)
                        }
                        tutorDatabase.classIncomingDao().setClassIncoming(classIncomingList)
                    }
                    result.value = ResultState.Success(res.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseHomeTutor>, t: Throwable) {
                result.value = ResultState.Error(t.message.toString())
            }
        })

        return result
    }


    companion object {
        @Volatile
        private var instance: TutoringRepository? = null
        fun getInstance(
            apiService: ApiService,
            prefTutor: PrefTutor,
            prefMain: PrefMain,
            appExecutor: AppExecutor,
            tutorDatabase: TutorDatabase
        ): TutoringRepository = instance ?: synchronized(this) {
            instance ?: TutoringRepository(
                apiService,
                prefTutor,
                prefMain,
                appExecutor,
                tutorDatabase
            )
        }.also { instance = it }
    }
}
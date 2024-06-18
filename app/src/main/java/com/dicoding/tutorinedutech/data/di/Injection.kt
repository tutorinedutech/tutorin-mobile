package com.dicoding.tutorinedutech.data.di

import android.content.Context
import com.dicoding.tutorinedutech.data.db.learner.LearnerDatabase
import com.dicoding.tutorinedutech.data.db.tutor.TutorDatabase
import com.dicoding.tutorinedutech.data.repository.UserRepository
import com.dicoding.tutorinedutech.data.retrofit.ApiConfig
import com.dicoding.tutorinedutech.utils.AppExecutor
import com.dicoding.tutorinedutech.utils.PrefLearner
import com.dicoding.tutorinedutech.utils.PrefMain
import com.dicoding.tutorinedutech.utils.PrefTutor
import com.dicoding.tutorinedutech.utils.learnerDataStore
import com.dicoding.tutorinedutech.utils.mainDataStore
import com.dicoding.tutorinedutech.utils.tutorDataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val learnerDatabase = LearnerDatabase.getDatabase(context)
        val tutorDatabase = TutorDatabase.getDatabase(context)
        val prefTutor = PrefTutor.getInstance(context.tutorDataStore)
        val prefLearner = PrefLearner.getInstance(context.learnerDataStore)
        val prefMain = PrefMain.getInstance(context.mainDataStore)
        val apiService = ApiConfig.getApiService(prefTutor, prefLearner)
        val appExecutor = AppExecutor()

        return UserRepository.getInstance(apiService, prefLearner, prefTutor, prefMain, appExecutor, learnerDatabase, tutorDatabase)
    }
}
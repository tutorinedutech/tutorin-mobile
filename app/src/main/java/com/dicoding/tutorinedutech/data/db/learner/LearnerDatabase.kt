package com.dicoding.tutorinedutech.data.db.learner

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Learner::class],
    version = 1,
    exportSchema = false
)
abstract class LearnerDatabase : RoomDatabase() {
    abstract fun learnerDao(): LearnerDao

    companion object {
        @Volatile
        private var INSTANCE: LearnerDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): LearnerDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    LearnerDatabase::class.java, "learner_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
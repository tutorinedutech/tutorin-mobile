package com.dicoding.tutorinedutech.data.db.tutor

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Tutor::class, Classes::class, ClassDetail::class, ClassIncoming::class],
    version = 2,
    exportSchema = false
)
abstract class TutorDatabase : RoomDatabase() {
    abstract fun tutorDao(): TutorDao
    abstract fun classesDao(): ClassDao
    abstract fun classDetailDao(): ClassDetailDao
    abstract fun classIncomingDao(): ClassIncomingDao

    companion object {
        @Volatile
        private var INSTANCE: TutorDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): TutorDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TutorDatabase::class.java, "tutor_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
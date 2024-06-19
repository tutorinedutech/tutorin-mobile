package com.dicoding.tutorinedutech.data.db.tutor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ClassDao {
    @Query("DELETE FROM classes")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setClass(classes: List<Classes>)

    @Update(entity = Classes::class)
    fun updateClass(classObject: Classes)

    @Query("SELECT * FROM classes")
    fun getAllClass(): LiveData<List<Classes>>

    @Transaction
    @Query("SELECT * FROM classes")
    fun getAllClassWDetail(): LiveData<List<ClassesAndClassDetail>>
}

package com.dicoding.tutorinedutech.data.db.tutor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClassDetailDao {
    @Query("DELETE FROM class_detail")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setClassDetail(classes: List<ClassDetail>)

    @Update(entity = ClassDetail::class)
    fun updateClassDetail(classObject: ClassDetail)

    @Query("SELECT * FROM class_detail")
    fun getAllClassDetail(): LiveData<List<ClassDetail>>
}
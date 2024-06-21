package com.dicoding.tutorinedutech.data.db.tutor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClassIncomingDao {
    @Query("DELETE FROM class_incoming")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setClassIncoming(classes: List<ClassIncoming>)

    @Update(entity = ClassIncoming::class)
    fun updateClassIncoming(classObject: ClassIncoming)

    @Query("SELECT * FROM class_incoming")
    fun getAllClassIncoming(): LiveData<List<ClassIncoming>>
}
package com.dicoding.tutorinedutech.data.db.tutor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: Tutor)

    @Query("SELECT * FROM user_tutor ORDER BY ROWID ASC LIMIT 1")
    fun getUser(): Tutor?

    @Delete(entity = Tutor::class)
    fun delete()

    @Update(entity = Tutor::class)
    fun updateUser(userObject: Tutor)
}
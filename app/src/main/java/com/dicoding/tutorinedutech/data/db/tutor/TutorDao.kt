package com.dicoding.tutorinedutech.data.db.tutor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.tutorinedutech.data.db.learner.Learner

@Dao
interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: Tutor)

    @Query("SELECT * FROM user_tutor ORDER BY ROWID ASC LIMIT 1")
    fun getUser(): Tutor?

    @Query("DELETE FROM user_tutor")
    fun delete()

    @Update(entity = Tutor::class)
    fun updateUser(userObject: Tutor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCreateUser(user: Tutor)

    @Query("SELECT * FROM user_tutor WHERE id = 0 AND userId = 0")
    fun getCreateUser(): LiveData<Tutor?>

    @Update(entity = Tutor::class)
    fun updateCreateUser(userObject: Tutor)
}
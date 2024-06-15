package com.dicoding.tutorinedutech.data.db.learner

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LearnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: Learner)

    @Query("SELECT * FROM user_learner ORDER BY ROWID ASC LIMIT 1")
    fun getUser(): LiveData<Learner?>

    @Query("DELETE FROM user_learner")
    fun delete()

    @Update(entity = Learner::class)
    fun updateUser(userObject: Learner)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCreateUser(user: Learner)

    @Query("SELECT * FROM user_learner WHERE id = 0")
    fun getCreateUser(): LiveData<Learner?>

    @Update(entity = Learner::class)
    fun updateCreateUser(userObject: Learner)
}
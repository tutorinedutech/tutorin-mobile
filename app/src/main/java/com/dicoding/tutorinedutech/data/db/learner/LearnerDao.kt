package com.dicoding.tutorinedutech.data.db.learner

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.tutorinedutech.data.db.tutor.Tutor

@Dao
interface LearnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: Learner)

    @Query("SELECT * FROM user_learner ORDER BY ROWID ASC LIMIT 1")
    fun getUser(): LiveData<Learner?>

    @Delete(entity = Learner::class)
    fun deleteById(id: String)

    @Update(entity = Learner::class)
    fun updateUser(userObject: Learner)
}
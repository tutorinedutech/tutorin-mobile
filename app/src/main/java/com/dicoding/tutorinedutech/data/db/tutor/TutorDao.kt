package com.dicoding.tutorinedutech.data.db.tutor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: Tutor)

    @Query("SELECT * FROM user_tutor WHERE id NOT LIKE 0 ORDER BY ROWID ASC LIMIT 1")
    fun getUser(): LiveData<Tutor?>

    @Query("DELETE FROM user_tutor")
    fun delete()

    @Update(entity = Tutor::class)
    fun updateUser(userObject: Tutor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCreateUser(userObject: Tutor)

    @Query("SELECT * FROM user_tutor WHERE id = 0 AND userId = 0")
    fun getCreateUser(): LiveData<Tutor?>

    @Update(entity = Tutor::class)
    fun updateCreateUser(userObject: TutorUpdateonCreate)

    @Update(entity = Tutor::class)
    fun updateHomeUser(userObject: TutorUpdateHome)

    @Query("UPDATE user_tutor SET ktp=:ktp, cv=:cv, profilePicture=:profile WHERE id != 0")
    fun updateUserFile(ktp: String, cv: String, profile: String)

    @Query("SELECT COUNT(*) FROM user_tutor WHERE id != 0")
    fun checkIfUserLoggedIn(): Int
}
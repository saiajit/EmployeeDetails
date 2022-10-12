package com.example.employeedetails.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {
    @Insert// annotations for ROOM
    suspend fun insertPersonDetails(person: PersonDetails)//Function
    @Update
    suspend fun updatePD(person: PersonDetails)
    @Delete
    suspend fun delPD(person: PersonDetails)

    @Query("Select * From PersonDeets") // no need of coroutines for Query
    fun getAllDeets():LiveData<List<PersonDetails>>

}
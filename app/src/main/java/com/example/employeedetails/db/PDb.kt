package com.example.employeedetails.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonDetails::class], version = 1, exportSchema = false)
abstract class PDb: RoomDatabase() {
    // write function for Dao which related to database

    abstract fun PersonDao(): PersonDao
    companion object{

        private var INSTANCE : PDb? = null
        fun getInstance(context: Context): PDb{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PDb::class.java,
                        "DEETS"
                    ).build()
                }

                return instance
            }

        }
    }

}
package com.example.employeedetails.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PersonDeets")
data class PersonDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "personId")
    var id:Int,
    @ColumnInfo(name = "person_name")
    var name: String,
    @ColumnInfo(name = "person_email")
    var email: String)
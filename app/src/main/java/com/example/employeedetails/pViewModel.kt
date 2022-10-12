package com.example.employeedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedetails.db.PersonDao
import com.example.employeedetails.db.PersonDetails
import kotlinx.coroutines.launch

class pViewModel(private val dao: PersonDao):ViewModel() {

    val pdetails = dao.getAllDeets()

    fun insertPersonDetails(person: PersonDetails)=viewModelScope.launch {
        dao.insertPersonDetails(person)
    }
    fun updatePersonDetails(person: PersonDetails)=viewModelScope.launch {
        dao.updatePD(person)
    }
    fun delPersonDetails(person: PersonDetails)=viewModelScope.launch {
        dao.delPD(person)
    }
}
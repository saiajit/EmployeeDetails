package com.example.employeedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeedetails.db.PersonDao
import java.lang.IllegalArgumentException

class PViewModelFactory(
    private val dao: PersonDao
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(pViewModel::class.java)){
            return pViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
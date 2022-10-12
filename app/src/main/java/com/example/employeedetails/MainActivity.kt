package com.example.employeedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import androidx.lifecycle.ViewModelProvider

import com.example.employeedetails.db.PDb

import com.example.employeedetails.db.PersonDetails

class MainActivity : AppCompatActivity() {

    private lateinit var nameET : EditText
    private lateinit var EmailET : EditText
    private lateinit var savebtn : Button
    private lateinit var clearbtn : Button

    private lateinit var viewModel: pViewModel //m


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameET = findViewById(R.id.et_name)
        EmailET = findViewById(R.id.et_Email)
        savebtn = findViewById(R.id.btnSave)
        clearbtn = findViewById(R.id.btnClear)

        val dao= PDb.getInstance(application).PersonDao()
        val factory= pViewModel(dao)
        viewModel = ViewModelProvider(this,PViewModelFactory(dao)).get(pViewModel::class.java)//m

        savebtn.setOnClickListener{saveDeets();clearinp()}
        clearbtn.setOnClickListener{clearinp() }
    }
    private fun saveDeets(){
        viewModel.insertPersonDetails(
            PersonDetails(0,
            nameET.text.toString(),
            EmailET.text.toString()
            )
        )
    }
    private fun clearinp(){
        nameET.setText("")
        EmailET.setText("")
    }
}
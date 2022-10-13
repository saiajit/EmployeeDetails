package com.example.employeedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.employeedetails.db.PDb

import com.example.employeedetails.db.PersonDetails

class MainActivity : AppCompatActivity() {

    private lateinit var nameET : EditText
    private lateinit var EmailET : EditText
    private lateinit var savebtn : Button
    private lateinit var clearbtn : Button
    private lateinit var PersonRV : RecyclerView
    private  val adap: PersonRVAdap = PersonRVAdap()
    private lateinit var viewModel: pViewModel //m


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameET = findViewById(R.id.et_name)
        EmailET = findViewById(R.id.et_Email)
        savebtn = findViewById(R.id.btnSave)
        clearbtn = findViewById(R.id.btnClear)
        PersonRV = findViewById(R.id.rvDeatails)

        initRecyclerview()

        val dao= PDb.getInstance(application).PersonDao()
        val factory= PViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory).get(pViewModel::class.java)//m

        savebtn.setOnClickListener{saveDeets();clearinp()}
        clearbtn.setOnClickListener{clearinp() }

        Log.e("MyTag","message called")

    }
    private fun saveDeets(){
        viewModel.insertPersonDetails(
            PersonDetails(0,
            nameET.text.toString(),
            EmailET.text.toString()
            )
        )
        displayList()
        Log.e("MyTag","message called12")
    }
    private fun clearinp(){
        nameET.setText("")
        EmailET.setText("")
    }
    private fun initRecyclerview(){
        PersonRV.layoutManager= LinearLayoutManager(this)

        PersonRV.adapter = adap
        // constructing recycler View
        displayList()
    }
    private fun displayList(){
        if(::viewModel.isInitialized) {
            viewModel.pdetails.observe(this) {
                adap.setList(it)// passing list to adapter using setList (it represents List in Live Data)
                adap.notifyDataSetChanged()
                Log.e("MyTag","message called123")
            }
        }else {
            val dao= PDb.getInstance(application).PersonDao()
            val factory= PViewModelFactory(dao)
            viewModel = ViewModelProvider(this,factory).get(pViewModel::class.java)//m

            viewModel.pdetails.observe(this) {
                adap.setList(it)// passing list to adapter using setList (it represents List in Live Data)
                adap.notifyDataSetChanged()
                Log.e("MyTag", "message called123")
            }
        }

    }

}
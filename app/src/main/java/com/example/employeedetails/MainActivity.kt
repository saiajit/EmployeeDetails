package com.example.employeedetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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
    private lateinit var adap: PersonRVAdap
    private lateinit var viewModel: pViewModel //m

    private lateinit var selectedPerson : PersonDetails
    private var isListItemCLicked = false
    private lateinit var userdeets: TextView
    private lateinit var backbutton: Button
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

        savebtn.setOnClickListener{
            if(isListItemCLicked){
                updateDeets()
                clearinp()
            }else {
                saveDeets()
                clearinp()
            }
        }
        clearbtn.setOnClickListener{
            if (isListItemCLicked){
                deleteDeets()
                clearinp()

            }else {
                clearinp()
            }
        }


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
    private fun updateDeets(){
        viewModel.updatePersonDetails(
            PersonDetails(
                selectedPerson.id,
                nameET.text.toString(),
                EmailET.text.toString()
            )
        )

        savebtn.text="Save"
        clearbtn.text="Clear"
        isListItemCLicked= false
    }
    private fun deleteDeets(){
        viewModel.delPersonDetails(
            PersonDetails(
                selectedPerson.id,
                nameET.text.toString(),
                EmailET.text.toString()
            )
        )
        savebtn.text="Save"
        clearbtn.text="Clear"
        isListItemCLicked= false

    }// pickUpAct showDee
    private fun clearinp(){
        nameET.setText("")
        EmailET.setText("")
    }
    private fun initRecyclerview(){
        PersonRV.layoutManager= LinearLayoutManager(this)
        adap = PersonRVAdap {
            selectedItem:PersonDetails -> ListItemCLicked(selectedItem)
        }
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
    private fun ListItemCLicked(person:PersonDetails){
//        Toast.makeText(
//            this,
//            "employee name is ${person.name}",
//            Toast.LENGTH_LONG
//        ).show()
        selectedPerson = person
        savebtn.text="Update"
        clearbtn.text="Delete"
        isListItemCLicked= true // boolean
        nameET.setText(selectedPerson.name)
        EmailET.setText(selectedPerson.email)

        showDeps(person)


    }

    fun showDeps(packet: PersonDetails) {

        var builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setCancelable(false)
        val inflater: LayoutInflater =
            applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.view_alert, null)
        view.visibility = View.VISIBLE
        builder.setView(view)
        userdeets=view.findViewById(R.id.Introtxt)
        backbutton= view.findViewById(R.id.backbtn)
        var alert = builder.show();
        userdeets.text="Hello, I am ${selectedPerson.name}"
        backbutton.setOnClickListener{
           alert.dismiss()
        }

        alert.setCanceledOnTouchOutside(true)
    }


}//Reposittory 232 how tto make get and post api call in kolin
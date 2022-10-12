package com.example.employeedetails
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedetails.db.PersonDetails

class PersonRVAdap():RecyclerView.Adapter<PersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val layoutinflater = LayoutInflater.from(parent.context)// iflating list item

        val listItem = layoutinflater.inflate(R.layout.list_item,parent,false)
        return PersonViewHolder(listItem)

    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}
class PersonViewHolder(private val view:View):RecyclerView.ViewHolder(view){
    fun bind(person : PersonDetails){
        val nametxt= view.findViewById<TextView>(R.id.tvName)
        val emailtxt= view.findViewById<TextView>(R.id.tvEmail)
        nametxt.text = person.name
        emailtxt.text = person.email
    }

}

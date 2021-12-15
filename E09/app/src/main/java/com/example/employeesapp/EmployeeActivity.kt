package com.example.employeesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val employeeString = bundle.getString("employee")
            if (employeeString != null) {
                val employee = JSONObject(employeeString)
                val name = employee["firstName"]
                Toast.makeText(this, "Name is $name",Toast.LENGTH_LONG).show()
                // find employees title element from layout
                //val titleTextView: TextView = findViewById(R.id.titleTextView)
                // show employees title
                //titleTextView.text = employee["title"].toString()
            }
        }
    }

}
package com.example.employeesapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.net.URL
import java.util.concurrent.ExecutorService

// Employees Adapter, used in RecyclerView in MainActivity
class EmployeesAdapter(private val employees: JSONArray)
    : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    // Create UI View Holder from XML layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : EmployeesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.employee_item, parent, false)
        return ViewHolder(view)
    }

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        // Add other Views in here: title, email, ...
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val emailTextView: TextView = view.findViewById(R.id.emailTextView)
        val phoneTextView: TextView = view.findViewById(R.id.phoneTextView)
        val departmentTextView: TextView = view.findViewById(R.id.departmentTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        // add a item click listener
        // Add a item click listener
        init {
            itemView.setOnClickListener {
                // remove or comment earlier Toast-message

                // create an explicit intent
                val intent = Intent(view.context, EmployeeActivity::class.java)
                // add selected employee JSON as a string data
                intent.putExtra("employee",employees[adapterPosition].toString())
                // start a new activity
                view.context.startActivity(intent)
            }
        }
        

        inner class AsyncLoadFile() : AsyncTask<String, Void, Bitmap>() {
            override fun doInBackground(vararg params: String?): Bitmap {
                try {
                    val url = URL(params[0])
                    val connection = url.openConnection()
                    val stream = connection.getInputStream()
                    val bmp = BitmapFactory.decodeStream(stream)
                    return bmp
                } catch (exc: IOException) {
                    Log.e("IOError", exc.message.toString())
                } catch (exc: Exception) {
                    Log.e("Error", exc.message.toString())
                }

                throw Exception()
            }

            override fun onPostExecute(result: Bitmap?) {
                super.onPostExecute(result)
                imageView.setImageBitmap(result)
            }
        }
    }


    // Bind data to UI View Holder
    override fun onBindViewHolder(
        holder: EmployeesAdapter.ViewHolder,
        position: Int)
    {
        // employee to bind UI
        val employee: JSONObject = employees.getJSONObject(position)
        // employee lastname and firstname
        // TASK: you can modify this to use formatting strings with placeholders
        holder.nameTextView.text =
            employee["lastName"].toString()+" "+ employee["firstName"].toString()
        // Add title, email, phone, department, image
        holder.titleTextView.text = employee["title"].toString()
        holder.emailTextView.text = employee["email"].toString()
        holder.phoneTextView.text = employee["phone"].toString()
        holder.departmentTextView.text = employee["department"].toString()



        // to get image context in Glide, you can use holder.imageView.context
//        try {
//            val url = URL(employee["image"].toString())
//            val connection = url.openConnection()
//            val stream = connection.getInputStream()
//            val bmp = BitmapFactory.decodeStream(stream)
//            holder.imageView.setImageBitmap(bmp)
//        } catch (exc: IOException) {
//            Log.e("IOError", exc.message.toString())
//        } catch (exc: Exception) {
//            Log.e("Error", exc.message.toString())
//        }
    }
    // Return item count in employees
    override fun getItemCount(): Int = employees.length()
}
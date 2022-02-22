package com.example.lastassignment01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*

class Employee(val id:String, val First:String, val Last:String){
}

class MainActivity : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        analytics = FirebaseAnalytics.getInstance(this)

        save.setOnClickListener {
            val First = fst.text.toString()
            val Last = sur.text.toString()

            val fb = FirebaseDatabase.getInstance()
            val ref = fb.getReference("Employee")
            val id:String? = ref.push().key

            val Employee = Employee(id.toString(), First, Last)
            ref.child(id.toString()).setValue(Employee).addOnCompleteListener {
                Toast.makeText(applicationContext,"Complete",Toast.LENGTH_LONG).show()
                fst.setText("")
                sur.setText("")
            }
        }
    }
}
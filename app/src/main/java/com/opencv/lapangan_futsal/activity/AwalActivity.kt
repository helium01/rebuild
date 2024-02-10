package com.opencv.lapangan_futsal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.opencv.lapangan_futsal.R

class AwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_awal)
        mainbutton()
    }
    fun mainbutton(){
        val myButton = findViewById<Button>(R.id.btn_login)
        myButton.setOnClickListener{
//            s.setStatusLogin(true)
            startActivity(Intent(this,LoginActivity::class.java) )
        }
        val myregister = findViewById<Button>(R.id.btn_register)
        myregister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java) )
        }
        val myregister_outlite = findViewById<Button>(R.id.btn_register_otlite)
        myregister_outlite.setOnClickListener {
            startActivity(Intent(this,RegisterOutliteActivity::class.java) )
        }
    }
}
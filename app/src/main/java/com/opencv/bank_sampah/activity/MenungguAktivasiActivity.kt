package com.opencv.bank_sampah.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.opencv.bank_sampah.AdminOutliteActivity
import com.opencv.bank_sampah.MainActivity
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.app.ApiConfig
import com.opencv.bank_sampah.app.ApiService
import com.opencv.bank_sampah.helper.SharePref
import com.opencv.bank_sampah.model.response.outliteResponseData
import retrofit2.Call
import retrofit2.Response

class MenungguAktivasiActivity : AppCompatActivity() {
    lateinit var s: SharePref
    override fun onCreate(savedInstanceState: Bundle?) {
        s = SharePref(this)
        val retro= ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.outliteData(s.getId()).enqueue(object : retrofit2.Callback<outliteResponseData>{
            override fun onResponse(call: Call<outliteResponseData>, response: Response<outliteResponseData>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val status = apiResponse?.status
                    val userList = apiResponse?.data

                    if(status=="sudah di validasi"){

//                        Toast.makeText(this@MenungguAktivasiActivity, "Selamat datang " , Toast.LENGTH_SHORT).show()
//                        val intent= Intent(this@MenungguAktivasiActivity, AdminOutliteActivity::class.java)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
//                        startActivity(intent)
//                        finish()
                    }
                    // Lakukan sesuatu dengan token dan data user
//                    Log.e("data",userResponse.toString())
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    Log.e("error", response.code().toString())
                }
            }

            override fun onFailure(call: Call<outliteResponseData>, t: Throwable) {
                Log.e("error",t.message.toString())
            }

        })
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menunggu_aktivasi)
        val nama_outlite = findViewById<Button>(R.id.buttonkeluar)
        nama_outlite.setOnClickListener {
            s.setStatusLogin(false)
            val intent= Intent(this@MenungguAktivasiActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
            startActivity(intent)
            finish()
        }

    }
}
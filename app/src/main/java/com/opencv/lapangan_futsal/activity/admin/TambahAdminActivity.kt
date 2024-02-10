package com.opencv.lapangan_futsal.activity.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.adapter.UserAdapter
import com.opencv.lapangan_futsal.app.ApiConfig
import com.opencv.lapangan_futsal.app.ApiService
import com.opencv.lapangan_futsal.model.response.userResponseGet
import retrofit2.Call
import retrofit2.Response

class TambahAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_admin_tambah_admin)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val listViewUsers: ListView = findViewById(R.id.listViewUsers)
        val retro=ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.lihatUser().enqueue(object : retrofit2.Callback<userResponseGet>{
            override fun onResponse(call: Call<userResponseGet>, response: Response<userResponseGet>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val status = apiResponse?.status
                    val userList = apiResponse?.data

                    if (userList != null) {
                        val adapter = UserAdapter(this@TambahAdminActivity, userList)
                        listViewUsers.adapter = adapter
                    }

                 else {
                    // Tangani kesalahan respons API
                }

                    // Lakukan sesuatu dengan token dan data user
//                    Log.e("data",userResponse.toString())
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    Log.e("error","error mas")
                }
            }

            override fun onFailure(call: Call<userResponseGet>, t: Throwable) {
                Log.e("error",t.message.toString())
            }

        })

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
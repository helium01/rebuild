package com.opencv.bank_sampah.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.opencv.bank_sampah.MainActivity
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.adapter.UserAdapter
import com.opencv.bank_sampah.app.ApiConfig
import com.opencv.bank_sampah.app.ApiService
import com.opencv.bank_sampah.model.data.User
import com.opencv.bank_sampah.model.data.modeluser
import com.opencv.bank_sampah.model.response.userResponse
import retrofit2.Call
import retrofit2.Response

class TambahAdminActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_admin_tambah_admin)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        listView = findViewById(R.id.listViewUserTambah)
        userAdapter = UserAdapter(this, emptyList())
        listView.adapter = userAdapter
        Log.e("data","data")

        val retro=ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.lihatUser().enqueue(object : retrofit2.Callback<List<modeluser>>{
            override fun onResponse(call: Call<List<modeluser>>, response: Response<List<modeluser>>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
//                    userResponse?.let {
//                        Log.d("Response", "Users: $it")
//                    }
//                    val data = userResponse
//                    if (data != null) {
//                        userAdapter.clear()
//                        userAdapter.addAll(data)
//                    }

                    // Lakukan sesuatu dengan token dan data user
//                    Log.e("data",userResponse.toString())
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    Log.e("error","error mas")
                }
            }

            override fun onFailure(call: Call<List<modeluser>>, t: Throwable) {
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
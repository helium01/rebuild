package com.opencv.lapangan_futsal.activity.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.adapter.RequestOutliteAdapter
import com.opencv.lapangan_futsal.app.ApiConfig
import com.opencv.lapangan_futsal.app.ApiService
import com.opencv.lapangan_futsal.model.response.outliteResponseGet
import retrofit2.Call
import retrofit2.Response

class RequestOutliteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_admin_request_outlite)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        hitData()
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
    fun hitData(){
        Log.e("disini","okay")
        val listViewUsers: ListView = findViewById(R.id.listViewRequestOutlite)
        val retro= ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.indexBelumValid().enqueue(object : retrofit2.Callback<List<outliteResponseGet>>{
            override fun onResponse(call: Call<List<outliteResponseGet>>, response: Response<List<outliteResponseGet>>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
//                    val userList = apiResponse?.data

                    if (apiResponse != null) {
                        if(apiResponse.size == 0){
                            val emptyMessage = "Tidak ada data yang ditampilkan"
                            val toast = Toast.makeText(this@RequestOutliteActivity.applicationContext, emptyMessage, Toast.LENGTH_SHORT)

                            toast.show()
                            val adapter = RequestOutliteAdapter(this@RequestOutliteActivity, apiResponse)
                            listViewUsers.adapter = adapter
                        }else{
                            val adapter = RequestOutliteAdapter(this@RequestOutliteActivity, apiResponse)
                            listViewUsers.adapter = adapter

                        }
                    }

//                    else {
//                        Log.e("data","disini error")
//                        // Tangani kesalahan respons API
//                    }

                    // Lakukan sesuatu dengan token dan data user
//                    Log.e("data",userResponse.toString())
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    Log.e("error","error mas")
                }
            }

            override fun onFailure(call: Call<List<outliteResponseGet>>, t: Throwable) {
                Log.e("error",t.message.toString())
            }

        })
    }
}
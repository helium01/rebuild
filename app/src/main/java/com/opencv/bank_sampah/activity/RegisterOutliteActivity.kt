package com.opencv.bank_sampah.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.opencv.bank_sampah.MainActivity
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.app.ApiConfig
import com.opencv.bank_sampah.app.ApiService
import com.opencv.bank_sampah.helper.SharePref
import com.opencv.bank_sampah.model.request.registerRequest
import com.opencv.bank_sampah.model.response.userResponse
import retrofit2.Call
import retrofit2.Response

class RegisterOutliteActivity : AppCompatActivity() {
    private lateinit var selectedRole: String
    private lateinit var s: SharePref
    override fun onCreate(savedInstanceState: Bundle?) {
        s = SharePref(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_outlite)
        val myButton = findViewById<Button>(R.id.btn_register)
        myButton.setOnClickListener {
            register()
        }
        val role = findViewById<Spinner>(R.id.txt_role)
        role.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedRole = parent.getItemAtPosition(position).toString()
                // Lakukan sesuatu dengan data yang dipilih oleh pengguna

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                role.requestFocus()
                return
            }
        }
    }
    fun register(){
        val nama = findViewById<TextView>(R.id.txt_nama)
        val email = findViewById<TextView>(R.id.txt_email)
        val password = findViewById<TextView>(R.id.txt_password)
        val pb = findViewById<ProgressBar>(R.id.progres_bar)
        pb.visibility= View.VISIBLE

        if(nama.text.isEmpty()){
            nama.error="kolom nama tidak boleh kosong"
            nama.requestFocus()
            return
        }
        if(email.text.isEmpty()){
            email.error="kolom email tidak boleh kosong"
            email.requestFocus()
            return
        }
        if(password.text.isEmpty()){
            password.error="kolom password tidak boleh kosong"
            password.requestFocus()
            return
        }
        val request = registerRequest()
        request.email=email.text.toString().trim()
        request.password=password.text.toString().trim()
        request.name=nama.text.toString().trim()
        request.role=selectedRole.toString().trim()
        val retro= ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.register(request).enqueue(object : retrofit2.Callback<userResponse> {
            override fun onResponse(call: Call<userResponse>, response: Response<userResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val data = userResponse?.data
//                    val token = userResponse?.access_token
                    s.setStatusLogin(true)
                    s.setString("name",data?.name.toString())
                    s.setString("role",data?.role.toString())
                    s.setString("email",data?.email.toString())
                    data?.id?.let { s.setId(it.toInt()) }
                    Toast.makeText(this@RegisterOutliteActivity, "Selamat datang " + data?.name.toString(), Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@RegisterOutliteActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
                    startActivity(intent)
                    finish()
                    Log.e("token",data?.name.toString())
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    Log.e("error","error mas")
                }
            }

            override fun onFailure(call: Call<userResponse>, t: Throwable) {
                Log.e("error",t.message.toString())
            }

        })
    }
}
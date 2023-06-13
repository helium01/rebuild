package com.opencv.bank_sampah.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.app.ApiConfig
import com.opencv.bank_sampah.app.ApiService
import com.opencv.bank_sampah.helper.SharePref
import com.opencv.bank_sampah.model.request.outliteRequest
import com.opencv.bank_sampah.model.response.outliteResponse
import retrofit2.Call
import retrofit2.Response

class InpurOutliteActivity : AppCompatActivity() {
    private lateinit var s: SharePref
    private val PERMISSION_REQUEST_CODE = 123
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        s = SharePref(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inpur_outlite)
       lokasi()

    }
    fun login(){
        val id_user=s.getUser()?.id
        val nama_outlite = findViewById<EditText>(R.id.txt_nama_outlite)
        val kodepos = findViewById<EditText>(R.id.txt_kodepos)
        val long = findViewById<EditText>(R.id.txt_long)
        val lat = findViewById<EditText>(R.id.txt_lat)
        val pb = findViewById<ProgressBar>(R.id.progres_bar)
        val alamat = findViewById<EditText>(R.id.txt_alamat)
        if(nama_outlite.text.isEmpty()){
            nama_outlite.error="kolom email tidak boleh kosong"
            nama_outlite.requestFocus()
            return
        }
        if(kodepos.text.isEmpty()){
            kodepos.error="kolom password tidak boleh kosong"
            kodepos.requestFocus()
            return
        }
        if(long.text.isEmpty()){
            long.error="kolom password tidak boleh kosong"
            long.requestFocus()
            return
        }
        if(lat.text.isEmpty()){
            lat.error="kolom password tidak boleh kosong"
            lat.requestFocus()
            return
        }

        if(alamat.text.isEmpty()){
            alamat.error="kolom password tidak boleh kosong"
            alamat.requestFocus()
            return
        }
        pb.visibility= View.VISIBLE
        val request = outliteRequest()
        if (id_user != null) {
            request.id_user=id_user.toInt()
        }
        request.nama_outlite=nama_outlite.text.toString().trim()
//        request.kodepos=kodepos.text.toString().trim()
        request.lat=lat.text.toString().toDouble()
        request.lng=lat.text.toString().toDouble()
        request.alamat=alamat.text.toString().trim()
        val retro= ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.outlite(request).enqueue(object : retrofit2.Callback<outliteResponse> {
            override fun onResponse(call: Call<outliteResponse>, response: Response<outliteResponse>) {
                pb.visibility= View.GONE
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val data = userResponse?.data
                    data?.id?.let { s.setId(it.toInt()) }
                    Toast.makeText(this@InpurOutliteActivity, "Terimakasih telah mendaftar " + data?.nama_outlite, Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@InpurOutliteActivity, MenungguAktivasiActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
                    startActivity(intent)
                    finish()
                    // Lakukan sesuatu dengan token dan data user
                    Log.e("token",data?.nama_outlite.toString())
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    Log.e("error","error mas")
                }
            }

            override fun onFailure(call: Call<outliteResponse>, t: Throwable) {
                pb.visibility= View.GONE
                Log.e("error",t.message.toString())
            }

        })
    }

    fun lokasi(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude

                updateLocation(latitude, longitude)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
    }

    private fun updateLocation(latitude: Double, longitude: Double) {
        val latitudeTextView = findViewById<TextView>(R.id.txt_lat)
        val longitudeTextView = findViewById<TextView>(R.id.txt_long)
        Log.e("latitude",latitudeTextView.toString())
        latitudeTextView.text = latitude.toString()
        longitudeTextView.text = longitude.toString()
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_CODE)
            return
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            0, 0f, locationListener)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requestLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(locationListener)
    }
}

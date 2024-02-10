package com.opencv.lapangan_futsal.activity.user

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.opencv.lapangan_futsal.MainActivity
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.app.ApiConfig
import com.opencv.lapangan_futsal.app.ApiService
import com.opencv.lapangan_futsal.helper.SharePref
import com.opencv.lapangan_futsal.model.request.jemputRequest
import com.opencv.lapangan_futsal.model.response.jemputResponse
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class JemputActivity : AppCompatActivity() {
    private lateinit var selectedRole: String
    private lateinit var s: SharePref
//    private val PERMISSION_REQUEST_CODE = 123
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener


    private lateinit var editTextTanggal: EditText
    private val PERMISSION_REQUEST_CODE = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        s = SharePref(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_jemput)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
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

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        editTextTanggal = findViewById(R.id.editTextTanggal)
        val buttonSubmit: Button = findViewById(R.id.button_submit)

        editTextTanggal.setOnClickListener {
            showDatePicker()
        }
        val kategori_sampah = findViewById<Spinner>(R.id.spinnerKategori)
        kategori_sampah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedRole = parent.getItemAtPosition(position).toString()
                // Lakukan sesuatu dengan data yang dipilih oleh pengguna

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                kategori_sampah.requestFocus()
                return
            }
        }
        lokasi()

        buttonSubmit.setOnClickListener {
            // Proses logika pengiriman data atau tindakan yang sesuai
            kirimdata()
        }
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
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, monthOfYear)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val dateFormat = SimpleDateFormat("yyy/MM/dd", Locale.getDefault())
                val dateString = dateFormat.format(selectedDate.time)
                editTextTanggal.setText(dateString)
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }
    fun kirimdata(){
        val id_user = s.getId()
        val tanggal=findViewById<EditText>(R.id.editTextTanggal)
        val alamat=findViewById<EditText>(R.id.editTextAlamat)
        val catatan=findViewById<EditText>(R.id.editTextCatatan)
        val pb = findViewById<ProgressBar>(R.id.progres_bar)
        if(tanggal.text.isEmpty()){
            tanggal.error="kolom email tidak boleh kosong"
            tanggal.requestFocus()
            return
        }
        if(alamat.text.isEmpty()){
            alamat.error="kolom password tidak boleh kosong"
            alamat.requestFocus()
            return
        }
        if(catatan.text.isEmpty()){
            catatan.error="kolom password tidak boleh kosong"
            catatan.requestFocus()
            return
        }
        pb.visibility= View.VISIBLE
        val request = jemputRequest()
        request.alamat=alamat.text.toString().trim()
        request.tanggal=tanggal.text.toString().trim()
        request.id_user=id_user
        request.catatan=catatan.text.toString().trim()
        request.lat=globalLatitude
        request.lng=globalLongitude
        Log.e("lat",globalLatitude.toString())
        request.kategori_sampah=selectedRole.toString().trim()
        val retro= ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.jemput(request).enqueue(object : retrofit2.Callback<jemputResponse> {
            override fun onResponse(call: Call<jemputResponse>, response: Response<jemputResponse>) {
                pb.visibility= View.GONE
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val data = userResponse?.data
                    Log.e("status",data.toString())
//                    val token = userResponse?.access_token
                    data?.id?.let { s.setId(it.toInt()) }
                    Toast.makeText(this@JemputActivity, "Selamat datang " , Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@JemputActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
                    startActivity(intent)
                    finish()
                    // Lakukan sesuatu dengan token dan data user
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    val errorBody = response.errorBody()?.string()
                    Log.e("error", errorBody ?: "Unknown error")
                }
            }

            override fun onFailure(call: Call<jemputResponse>, t: Throwable) {
                pb.visibility= View.GONE
                Log.e("error",t.message.toString())
            }

        })
    }
    private var globalLatitude: Double = 0.0
    private var globalLongitude: Double = 0.0
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



    override fun onResume() {
        super.onResume()
        requestLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(locationListener)
    }

    private fun updateLocation(latitude: Double, longitude: Double) {
//        val alamat = findViewById<TextView>(R.id.test)
//        Log.e("okay",alamat.toString())
        globalLatitude=latitude
        globalLongitude=longitude
//        alamat.text = "Latitude: $latitude, Longitude: $longitude"

    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this,
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

}
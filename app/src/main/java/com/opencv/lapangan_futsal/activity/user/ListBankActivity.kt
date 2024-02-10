package com.opencv.lapangan_futsal.activity.user

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.adapter.BankSampahAdapter
import com.opencv.lapangan_futsal.app.ApiConfig
import com.opencv.lapangan_futsal.app.ApiService
import com.opencv.lapangan_futsal.model.response.carioutliteResponseData
import retrofit2.Call
import retrofit2.Response

class ListBankActivity : AppCompatActivity() {
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 1

    private lateinit var listViewBankSampah: ListView
    private lateinit var bankSampahAdapter: BankSampahAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list_bank)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

//        mendapatkan lokasi
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                locationPermissionCode
            )
            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener
        )

    }
    private fun updateListView(latitude: Double, longitude: Double) {
        val listViewUsers: ListView = findViewById(R.id.listViewBankSampah)
        val retro = ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.cariOutlites(latitude, longitude).enqueue(object : retrofit2.Callback<carioutliteResponseData> {
            override fun onResponse(call: Call<carioutliteResponseData>, response: Response<carioutliteResponseData>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val status = apiResponse?.status
                    val userList = apiResponse?.data
                    if (userList != null) {
                        val adapter = BankSampahAdapter(this@ListBankActivity, userList)
                        listViewUsers.adapter = adapter
                        locationManager.removeUpdates(locationListener)
                    }
                } else {
                    Log.e("error", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<carioutliteResponseData>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
        })
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val latitude = location.latitude
            val longitude = location.longitude
            // Gunakan latitude dan longitude sesuai kebutuhan Anda
            updateListView(latitude,longitude)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0f,
                        locationListener
                    )
                }
            }
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

}

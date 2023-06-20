package com.opencv.bank_sampah.activity.user

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.adapter.UserAdapter
import com.opencv.bank_sampah.app.ApiConfig
import com.opencv.bank_sampah.app.ApiService
import com.opencv.bank_sampah.model.response.carioutliteResponseData
import com.opencv.bank_sampah.model.response.outliteResponseData
import com.opencv.bank_sampah.model.response.userResponseGet
import retrofit2.Call
import retrofit2.Response
import kotlin.properties.Delegates

class PetaActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private val PERMISSION_REQUEST_CODE = 123
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_peta)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        lokasi()
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager



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
    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Konfigurasi peta di sini

        // Contoh penambahan marker pada lokasi tertentu



//        mendapatkan api dari outlite
        Log.e("status",globalLatitude.toString())
        var latitud =globalLatitude
        var long =globalLongitude
        if(globalLongitude==0.0){
            latitud=-7.916484
            long=112.634346
        }
        val retro= ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        retro.cariOutlites(latitud,long).enqueue(object : retrofit2.Callback<carioutliteResponseData>{
            override fun onResponse(call: Call<carioutliteResponseData>, response: Response<carioutliteResponseData>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val status = apiResponse?.status
                    val userList = apiResponse?.data
                    userList?.forEach{outlites ->
                        val latitude=outlites.lat
                        val longitude=outlites.lng
                        val location = LatLng(latitude, longitude)
                        Log.e("data",outlites.nama_outlite)
                        val marker=googleMap.addMarker(MarkerOptions().position(location).title(outlites.distance))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

                    }
                    val latitude = globalLatitude
                    val longitude = globalLongitude
                    val location = LatLng(latitude, longitude)
                    Log.e("data",latitude.toString())
//                    Log.e("data",outlites.nama_outlite)
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(location)
                            .title("Lokasi Saya")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    )

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

                    // Lakukan sesuatu dengan token dan data user
//                    Log.e("data",userResponse.toString())
                } else {
                    // Tangani error jika permintaan tidak berhasil
                    Log.e("error",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<carioutliteResponseData>, t: Throwable) {
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

//    untuk menambahkan lokasi saat ini
    private var globalLatitude: Double = 0.0
    private var globalLongitude: Double = 0.0


    private fun updateLocation(latitude: Double, longitude: Double) {
//        val latitudeTextView = findViewById<TextView>(R.id.txt_kodepos)
//        val longitudeTextView = findViewById<TextView>(R.id.txt_long)
//        Log.e("latitude",latitudeTextView.toString())
//        latitudeTextView.text = s.getId().toString()
//        longitudeTextView.text = longitude.toString()
        globalLatitude=latitude
        globalLongitude=longitude
        Log.e("status",globalLatitude.toString())

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
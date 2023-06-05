package com.opencv.bank_sampah.activity.user

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.adapter.BankSampahAdapter
import com.opencv.bank_sampah.model.data.BankSampah

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

        listViewBankSampah = findViewById(R.id.listViewBankSampah)

        // Ambil data dari API dan simpan dalam sebuah List<BankSampah>
        val bankSampahList = fetchDataFromApi()

        // Inisialisasi adapter dan set sebagai adapter untuk ListView
        bankSampahAdapter = BankSampahAdapter(this, bankSampahList)
        listViewBankSampah.adapter = bankSampahAdapter
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val latitude = location.latitude
            val longitude = location.longitude
            // Gunakan latitude dan longitude sesuai kebutuhan Anda
            println("Latitude: $latitude")
            println("Longitude: $longitude")
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
    private fun fetchDataFromApi(): List<BankSampah> {
        // Implementasi logika untuk mengambil data dari API
        // dan mengonversinya ke dalam List<BankSampah>
        // Misalnya, menggunakan Retrofit atau Volley untuk melakukan permintaan API
        // dan mengurai respons JSON ke dalam List<BankSampah>
        // Contoh sederhana:
        return listOf(
            BankSampah("Bank Sampah A", "dimas", "081234567890", "10km", -6.200000, 106.800000),
            BankSampah("Bank Sampah B", "joni", "082345678901", "12km", -6.300000, 106.900000),
            BankSampah("Bank Sampah C", "siti", "083456789012", "20km", -6.400000, 107.000000)
        )
    }
}

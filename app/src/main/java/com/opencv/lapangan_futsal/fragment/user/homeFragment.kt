package com.opencv.lapangan_futsal.fragment.user

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.activity.user.*
import com.opencv.lapangan_futsal.helper.SharePref

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var s: SharePref
    lateinit var viewnama: TextView
    lateinit var jemput: CardView
    lateinit var sedekah: CardView
    lateinit var peta: CardView
    lateinit var list_bank: CardView
    lateinit var kategori: CardView
    lateinit var riwayat: CardView
    lateinit var lokasi: Button
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_user_home, container, false)
        init(view)
        s= SharePref(requireActivity())
        setData()
        setInitLayout()
        return view
    }
    private fun setData() {
        viewnama.text=s.getString(s.name)
    }
    private fun init(view: View){
        viewnama=view.findViewById(R.id.name_view)
        jemput=view.findViewById(R.id.jemput)
//        sedekah=view.findViewById(R.id.sedekah)
        peta=view.findViewById(R.id.peta)
        list_bank=view.findViewById(R.id.list_bank)
//        kategori=view.findViewById(R.id.kategori)
//        riwayat=view.findViewById(R.id.riwayat)
        lokasi=view.findViewById(R.id.lihatsaya)
    }
    private fun setInitLayout() {
        jemput.setOnClickListener {
            startActivity(Intent(activity, JemputActivity::class.java))
        }
//        sedekah.setOnClickListener {
//            startActivity(Intent(activity, SedekahActivity::class.java))
//        }
        peta.setOnClickListener {
            startActivity(Intent(activity, PetaActivity::class.java))
        }
        list_bank.setOnClickListener {
            startActivity(Intent(activity, ListBankActivity::class.java))
        }
//        kategori.setOnClickListener {
//            startActivity(Intent(activity, KategoriActivity::class.java))
//        }
//        riwayat.setOnClickListener {
//            startActivity(Intent(activity, RiwayatActivity::class.java))
//        }
        lokasi.setOnClickListener {
            getLocation()
            Log.e("sini","sini")
        }
    }
    private fun getLocation() {
        // Cek izin lokasi
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Mendapatkan lokasi terkini menggunakan LocationManager
            val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, object :
                LocationListener {
                override fun onLocationChanged(location: Location) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val gmmIntentUri =
                        Uri.parse("geo:${latitude},${longitude}?z=15&q=${latitude},${longitude}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context?.startActivity(mapIntent)
                    // Gunakan latitude dan longitude sesuai kebutuhan Anda

                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }, null)
        } else {
            // Jika izin lokasi belum diberikan, minta izin dari aktivitas induk
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                com.opencv.lapangan_futsal.fragment.admin_outlet.homeFragment.PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            com.opencv.lapangan_futsal.fragment.admin_outlet.homeFragment.PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Izin diberikan, panggil fungsi untuk mendapatkan lokasi
                    getLocation()
                } else {
                    // Izin tidak diberikan, tangani kasus ini sesuai kebutuhan Anda
                    Toast.makeText(requireContext(), "Izin lokasi tidak diberikan", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
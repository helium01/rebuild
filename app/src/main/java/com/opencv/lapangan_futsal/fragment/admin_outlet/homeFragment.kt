package com.opencv.lapangan_futsal.fragment.admin_outlet

import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.activity.admin_outlite.Jemput_Outlite_Activity
import com.opencv.lapangan_futsal.activity.admin_outlite.Pencairan_Outlite_Activity
import com.opencv.lapangan_futsal.activity.admin_outlite.Sedekah_Outlite_Activity
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
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 1
    lateinit var s: SharePref
    lateinit var viewnama: TextView
    lateinit var pencairan: CardView
    lateinit var sedekah: CardView
    lateinit var jemput: CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View=inflater.inflate(R.layout.fragment_admin_outlite_home, container, false)
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
        pencairan=view.findViewById(R.id.pencairan)
//        sedekah=view.findViewById(R.id.sedekah)
        jemput=view.findViewById(R.id.jemput)
    }
    private fun setInitLayout() {
        pencairan.setOnClickListener {
            startActivity(Intent(activity, Pencairan_Outlite_Activity::class.java))
        }
        jemput.setOnClickListener {
            startActivity(Intent(activity, Jemput_Outlite_Activity::class.java))
        }
//        sedekah.setOnClickListener {
//            startActivity(Intent(activity, Sedekah_Outlite_Activity::class.java))
//        }

    }

    companion object {
         val PERMISSION_REQUEST_CODE = 1001
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
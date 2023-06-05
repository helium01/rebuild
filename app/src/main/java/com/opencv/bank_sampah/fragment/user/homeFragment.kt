package com.opencv.bank_sampah.fragment.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.opencv.bank_sampah.MainActivity
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.activity.user.*
import com.opencv.bank_sampah.helper.SharePref

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
        sedekah=view.findViewById(R.id.sedekah)
        peta=view.findViewById(R.id.peta)
        list_bank=view.findViewById(R.id.list_bank)
        kategori=view.findViewById(R.id.kategori)
        riwayat=view.findViewById(R.id.riwayat)
    }
    private fun setInitLayout() {
        jemput.setOnClickListener {
            startActivity(Intent(activity, JemputActivity::class.java))
        }
        sedekah.setOnClickListener {
            startActivity(Intent(activity, SedekahActivity::class.java))
        }
        peta.setOnClickListener {
            startActivity(Intent(activity, PetaActivity::class.java))
        }
        list_bank.setOnClickListener {
            startActivity(Intent(activity, ListBankActivity::class.java))
        }
        kategori.setOnClickListener {
            startActivity(Intent(activity, KategoriActivity::class.java))
        }
        riwayat.setOnClickListener {
            startActivity(Intent(activity, RiwayatActivity::class.java))
        }
    }

    companion object {
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
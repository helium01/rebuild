package com.opencv.lapangan_futsal.fragment.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.opencv.lapangan_futsal.MainActivity
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.activity.EditProfilActivity
import com.opencv.lapangan_futsal.helper.SharePref

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [akunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class akunFragment : Fragment() {
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
    lateinit var btnlogout: Button
    lateinit var btnedit: Button
    lateinit var viewnama: TextView
    lateinit var email: TextView
    lateinit var role: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_user_akun, container, false)
        init(view)
        s= SharePref(requireActivity())
        btnlogout.setOnClickListener {
            s.setStatusLogin(false)
            startActivity(Intent(activity, MainActivity::class.java))
        }
        btnedit.setOnClickListener {
            startActivity(Intent(activity, EditProfilActivity::class.java))
        }
        setData()

        return view
    }
    private fun setData() {
        viewnama.text=s.getString(s.name)
        email.text=s.getString(s.email)
        role.text=s.getString(s.role)
    }
    private fun init(view: View){
        btnlogout=view.findViewById(R.id.btn_logout)
        viewnama=view.findViewById(R.id.name_textview)
        email=view.findViewById(R.id.email_textview)
        role=view.findViewById(R.id.role_textview)
        btnedit=view.findViewById(R.id.edit_profile_button)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment akunFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            akunFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
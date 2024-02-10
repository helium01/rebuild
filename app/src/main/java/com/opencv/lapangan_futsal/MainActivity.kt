package com.opencv.lapangan_futsal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.opencv.lapangan_futsal.activity.AwalActivity
import com.opencv.lapangan_futsal.fragment.user.*
import com.opencv.lapangan_futsal.helper.SharePref
import com.opencv.lapangan_futsal.activity.InpurOutliteActivity

class MainActivity : AppCompatActivity() {

    private val FragmentHome : Fragment = homeFragment()
    private val FragmentAkun : Fragment = akunFragment()
//    private val FragmentJemput : Fragment = jemputFragment()
//    private val FragmentKategori : Fragment = kategoriFragment()
//    private val FragmentKategoriSampah : Fragment = kategoriSampahFragment()
//    private val FragmentPetaBankSampah : Fragment = petaBankSampahFragment()
//    private val FragmentRiwayat : Fragment = riwayatTransaksiFragment()
//    private val FragmentSedekah : Fragment = sedekahFragment()
    private val FragmentNotifikasi : Fragment = notifikasiFragment()
    private val fm : FragmentManager = supportFragmentManager
    private var active : Fragment = FragmentHome
    private lateinit var menu : Menu
    private lateinit var menuitem : MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private var status=false
    private lateinit var s: SharePref
    private val SPLASH_TIME_OUT = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembuka)
        s =SharePref(this)
        Handler().postDelayed({
            if(s.getStatusLogin()){
                if(s.getString(s.role)=="user"){
                    setContentView(R.layout.activity_main)
                    Log.e("masuk sini",s.getString(s.role))
                    setButtonNav()
                }else if(s.getString(s.role)=="admin_outlite"){
                    val intent= Intent(this@MainActivity, InpurOutliteActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
                    startActivity(intent)
                    finish()
                }else{
                    val intent= Intent(this@MainActivity, SuperAdminActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
                    startActivity(intent)
                    finish()
                    Log.e("masuksini",s.getString(s.role))
                }
            }else{
                startActivity(Intent(this, AwalActivity::class.java) )
            } // Menutup halaman awal agar tidak dapat dikembalikan dengan tombol back
        }, SPLASH_TIME_OUT.toLong())

    }

    fun setButtonNav(){
        fm.beginTransaction().add(R.id.container1,FragmentHome).show(FragmentHome).commit()
        fm.beginTransaction().add(R.id.container1,FragmentAkun).hide(FragmentAkun).commit()
        fm.beginTransaction().add(R.id.container1,FragmentNotifikasi).hide(FragmentNotifikasi).commit()

        bottomNavigationView=findViewById(R.id.nav_view)
        menu=bottomNavigationView.menu
        menuitem=menu.getItem(0)
        menuitem.isChecked=true

        bottomNavigationView.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.navigation_home->{
                    panggilfragment(0,FragmentHome)
                }
                R.id.navigation_notifisasi->{
                    panggilfragment(1,FragmentNotifikasi)
                }
                R.id.navigation_akun->{
                    panggilfragment(2,FragmentAkun)
                }

            }

            false
        }
    }
    fun panggilfragment(int: Int,fragment: Fragment){
        menuitem=menu.getItem(int)
        menuitem.isChecked=true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}
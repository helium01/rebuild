package com.opencv.bank_sampah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.opencv.bank_sampah.fragment.admin.akunFragment
import com.opencv.bank_sampah.fragment.admin.homeFragment
import com.opencv.bank_sampah.fragment.admin.notifikasiFragment
import com.opencv.bank_sampah.helper.SharePref

class SuperAdminActivity : AppCompatActivity() {

    private val FragmentHome : Fragment = homeFragment()
    private val FragmentAkun : Fragment = akunFragment()
    private val FragmentNotifikasi : Fragment = notifikasiFragment()
    private val fm : FragmentManager = supportFragmentManager
    private var active : Fragment = FragmentHome
    private lateinit var menu : Menu
    private lateinit var menuitem : MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private var status=false
    private lateinit var s: SharePref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_admin)
        setButtonNav()
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
                    panggilfragment(3,FragmentNotifikasi)
                }
                R.id.navigation_akun->{
                    panggilfragment(4,FragmentAkun)
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
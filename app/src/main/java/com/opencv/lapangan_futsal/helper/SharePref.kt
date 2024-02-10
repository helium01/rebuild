package com.opencv.lapangan_futsal.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.opencv.lapangan_futsal.model.data.User

class SharePref(activity : Activity) {
    val mypref="basecamp"
    val sp: SharedPreferences
    val login="login"
    val name="name"
    val email="email"
    val role="role"
    val id=""
    var user="user"

    init {
        sp=activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }
    fun setStatusLogin(status:Boolean){
        sp.edit().putBoolean(login,status).apply()
    }
    fun getStatusLogin():Boolean{
        return sp.getBoolean(login,false)
    }
    fun setString(key:String,value:String){
        sp.edit().putString(key,value).apply()
    }
    fun setUser(value: User){
        val data:String= Gson().toJson(value, User::class.java)
        sp.edit().putString(user,data).apply()
    }
    fun getUser(): User?{
        var data:String=sp.getString(user,null)?:return null
        val json= Gson().fromJson<User>(data, User::class.java)
        return json
    }
    fun setId(data:Int){
        sp.edit().putInt(id,data).apply()
    }
    fun getId():Int{
        return sp.getInt(id,0)
    }
    fun getString(key:String):String{
        return sp.getString(key,"")!!
    }
}
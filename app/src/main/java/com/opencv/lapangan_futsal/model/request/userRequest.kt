package com.opencv.lapangan_futsal.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class userRequest {
        @SerializedName("email")
        @Expose
        var email:String?=null

        @SerializedName("password")
        @Expose
        var password:String?=null
    }
    class registerRequest {
        @SerializedName("email")
        @Expose
        var email:String?=null

        @SerializedName("name")
        @Expose
        var name:String?=null

        @SerializedName("password")
        @Expose
        var password:String?=null

        @SerializedName("role")
        @Expose
        var role:String?=null
    }
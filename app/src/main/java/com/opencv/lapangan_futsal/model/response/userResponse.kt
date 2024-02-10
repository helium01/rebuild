package com.opencv.lapangan_futsal.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class userResponse {
    @SerializedName("data")
    @Expose
    var data:user?=null

//    @SerializedName("access_token")
//    @Expose
//    var access_token:String?=null

    class user{
        @SerializedName("id")
        @Expose
        var id:String?=null

        @SerializedName("name")
        @Expose
        var name:String?=null

        @SerializedName("email")
        @Expose
        var email:String?=null

        @SerializedName("password")
        @Expose
        var password:String?=null

        @SerializedName("role")
        @Expose
        var role:String?=null

    }
}
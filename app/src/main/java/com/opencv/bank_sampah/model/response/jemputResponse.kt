package com.opencv.bank_sampah.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class jemputResponse {
    @SerializedName("data")
    @Expose
    var data:jemput?=null

//    @SerializedName("access_token")
//    @Expose
//    var access_token:String?=null

    class jemput{
        @SerializedName("id")
        @Expose
        var id:String?=null

        @SerializedName("id_user")
        @Expose
        var id_user:Int?=null

        @SerializedName("id_outlite")
        @Expose
        var id_outlite:Int?=null

        @SerializedName("alamat")
        @Expose
        var alamat:String?=null

        @SerializedName("no_hp")
        @Expose
        var no_hp:String?=null


        @SerializedName("lat")
        @Expose
        var lat:Double?=null

        @SerializedName("lng")
        @Expose
        var lng:Double?=null

        @SerializedName("status")
        @Expose
        var status:String?=null
    }
}
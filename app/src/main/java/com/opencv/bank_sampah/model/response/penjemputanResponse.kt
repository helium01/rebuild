package com.opencv.bank_sampah.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class penjemputanResponse {
    @SerializedName("data")
    @Expose
    var data:outlitedata?=null

//    @SerializedName("access_token")
//    @Expose
//    var access_token:String?=null

    class outlitedata{
        @SerializedName("id")
        @Expose
        var id:String?=null

        @SerializedName("name")
        @Expose
        var id_user:String?=null

        @SerializedName("email")
        @Expose
        var jumlah_sampah:String?=null


        @SerializedName("email")
        @Expose
        var lng:String?=null

        @SerializedName("email")
        @Expose
        var lat:String?=null


    }
}
package com.opencv.bank_sampah.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class koinResponse {
    @SerializedName("data")
    @Expose
    var data:koindata?=null

//    @SerializedName("access_token")
//    @Expose
//    var access_token:String?=null

    class koindata{
        @SerializedName("id")
        @Expose
        var id:String?=null

        @SerializedName("name")
        @Expose
        var id_user:String?=null

        @SerializedName("email")
        @Expose
        var jumlah_koin:String?=null


    }
}
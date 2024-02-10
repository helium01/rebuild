package com.opencv.lapangan_futsal.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class riwayatResponse {
    @SerializedName("data")
    @Expose
    var data:riwayat?=null

//    @SerializedName("access_token")
//    @Expose
//    var access_token:String?=null

    class riwayat{
        @SerializedName("id")
        @Expose
        var id:String?=null

        @SerializedName("id_jemput")
        @Expose
        var id_jemput:Int?=null

        @SerializedName("nama")
        @Expose
        var nama:Int?=null

        @SerializedName("koin")
        @Expose
        var koin:Int?=null

        @SerializedName("status")
        @Expose
        var status:String?=null

    }
}
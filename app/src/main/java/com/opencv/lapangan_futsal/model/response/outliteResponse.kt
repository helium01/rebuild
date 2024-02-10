package com.opencv.lapangan_futsal.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class outliteResponse {
    @SerializedName("status")
    @Expose
    val status: String?=null
    @SerializedName("data")
    @Expose
    var data:sedekah?=null

//    @SerializedName("access_token")
//    @Expose
//    var access_token:String?=null

    class sedekah{
        @SerializedName("id")
        @Expose
        var id:String?=null

        @SerializedName("id_user")
        @Expose
        var id_user:Int?=null

        @SerializedName("nama_outlite")
        @Expose
        var nama_outlite:Int?=null

        @SerializedName("alamat")
        @Expose
        var alamat:Int?=null

        @SerializedName("status")
        @Expose
        var status:String?=null

        @SerializedName("lat")
        @Expose
        var lat:Double?=null

        @SerializedName("lng")
        @Expose
        var lng:Double?=null

    }
}
package com.opencv.bank_sampah.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class requestSampah {
    @SerializedName("id_user")
    @Expose
    var id_user:Int?=null
    @SerializedName("jumlah_sampah")
    @Expose
    var jumlah_sampah:Int?=null
    @SerializedName("status")
    @Expose
    var status:String?=null
}
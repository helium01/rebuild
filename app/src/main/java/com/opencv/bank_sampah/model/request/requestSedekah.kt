package com.opencv.bank_sampah.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class requestSedekah {
    @SerializedName("id_user")
    @Expose
    var id_user:Int?=null
    @SerializedName("id_sedekah")
    @Expose
    var jumlah_sampah:Int?=null
    @SerializedName("status")
    @Expose
    var status:String?=null
}
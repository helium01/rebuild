package com.opencv.bank_sampah.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class inputOutliteRequest {
    @SerializedName("id_user")
    @Expose
    var id_user:Int?=null
    @SerializedName("nama_outlite")
    @Expose
    var nama_outlite:String?=null
    @SerializedName("alamat")
    @Expose
    var alamat:String?=null
    @SerializedName("kodepos")
    @Expose
    var kodepos:String?=null
    @SerializedName("long")
    @Expose
    var long:Double?=null
    @SerializedName("long")
    @Expose
    var lat:Double?=null
}
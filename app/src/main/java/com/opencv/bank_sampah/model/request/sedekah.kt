package com.opencv.bank_sampah.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class sedekah {
    @SerializedName("id_outlite")
    @Expose
    var id_outlite:Int?=null
    @SerializedName("harga_koin")
    @Expose
    var harga_koin:Int?=null
    @SerializedName("nama_barang")
    @Expose
    var nama_barang:String?=null
    @SerializedName("deskeipsi")
    @Expose
    var deskeipsi:String?=null
}
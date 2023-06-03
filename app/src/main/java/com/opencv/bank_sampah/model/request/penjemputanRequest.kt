package com.opencv.bank_sampah.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class penjemputanRequest {
    @SerializedName("id_jenis_sampah")
    @Expose
    var id_jenis_sampah:Int?=null
    @SerializedName("id_user")
    @Expose
    var id_user:Int?=null
    @SerializedName("id_outlite")
    @Expose
    var id_outlite:Int?=null
    @SerializedName("tanggal_penjemputan")
    @Expose
    var tanggal_penjemputan:String?=null
    @SerializedName("alamat")
    @Expose
    var alamat:Double?=null
}
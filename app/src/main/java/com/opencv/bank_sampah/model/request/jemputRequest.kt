package com.opencv.bank_sampah.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class jemputRequest {
    @SerializedName("id_user")
    @Expose
    var id_user: Int? = null

    @SerializedName("id_outlite")
    @Expose
    var id_outlite: Int? = null

    @SerializedName("kategori_sampah")
    @Expose
    var kategori_sampah: String? = null

    @SerializedName("tanggal")
    @Expose
    var tanggal: String? = null

    @SerializedName("alamat")
    @Expose
    var alamat: String? = null

    @SerializedName("catatan")
    @Expose
    var catatan: String? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lng")
    @Expose
    var lng: Double? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}
package com.opencv.bank_sampah.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class sedekahRequest {
    @SerializedName("id_user")
    @Expose
    var id_user: Int? = null

    @SerializedName("id_outlite")
    @Expose
    var id_outlite: Int? = null

    @SerializedName("nama_sampah")
    @Expose
    var nama_sampah: String? = null

    @SerializedName("foto")
    @Expose
    var foto: String? = null

    @SerializedName("opsi")
    @Expose
    var opsi: String? = null

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
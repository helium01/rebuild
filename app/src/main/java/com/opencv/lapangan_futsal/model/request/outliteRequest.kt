package com.opencv.lapangan_futsal.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class outliteRequest {
    @SerializedName("id_user")
    @Expose
    var id_user: Int? = null

    @SerializedName("nama_outlite")
    @Expose
    var nama_outlite: String? = null

    @SerializedName("alamat")
    @Expose
    var alamat: String? = null

    @SerializedName("no_hp")
    @Expose
    var no_hp: String? = null

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
package com.opencv.lapangan_futsal.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class riwayatRequest {
    @SerializedName("id_user")
    @Expose
    var id_jemput: Int? = null

    @SerializedName("id_outlite")
    @Expose
    var koin: Int? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}
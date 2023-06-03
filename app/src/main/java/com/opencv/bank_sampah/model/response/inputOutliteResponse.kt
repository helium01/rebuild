package com.opencv.bank_sampah.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class inputOutliteResponse {
    @SerializedName("data")
    @Expose
    var data:inputOutlite?=null

//    @SerializedName("access_token")
//    @Expose
//    var access_token:String?=null

    class inputOutlite{
        @SerializedName("id")
        @Expose
        var id:String?=null

        @SerializedName("id_user")
        @Expose
        var id_user:String?=null

        @SerializedName("nama_outlite")
        @Expose
        var nama_outlite:String?=null

        @SerializedName("kodepos")
        @Expose
        var kodepos:String?=null

        @SerializedName("long")
        @Expose
        var long:String?=null

        @SerializedName("lat")
        @Expose
        var lat:String?=null

        @SerializedName("alamat")
        @Expose
        var alamat:String?=null

    }
}
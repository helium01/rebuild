package com.opencv.bank_sampah.model.response

data class outliteResponseGet (
    val id:Int,
    val id_user: String,
    val nama_outlite: String,
    val alamat: String,
    val no_hp: String,
    val status: String,
    val lng:Double,
    val lat:Double,
)
data class outliteResponseData (
    val status: String,
    val data:List<outlites>
)


    data class outlites(
    val id_user: String,
    val nama_outlite: String,
    val alamat: String,
    val no_hp: String,
    val status: String,
    val lng:Double,
    val lat:Double,
)

data class carioutliteResponseData (
    val status: String,
    val data:List<carioutlites>
)
data class carioutlites(
    val id_user: String,
    val nama_outlite: String,
    val alamat: String,
    val no_hp: String,
    val status: String,
    val lng:Double,
    val lat:Double,
    val distance:String
)
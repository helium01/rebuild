package com.opencv.bank_sampah.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class userResponseGet (
    val status: String,
    val data: List<users>
        )
data class users(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val role: String

)
package com.opencv.lapangan_futsal.model.response

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
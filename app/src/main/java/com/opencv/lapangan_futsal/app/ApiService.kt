package com.opencv.lapangan_futsal.app

import com.opencv.lapangan_futsal.model.request.jemputRequest
import com.opencv.lapangan_futsal.model.request.outliteRequest
import com.opencv.lapangan_futsal.model.request.registerRequest
import com.opencv.lapangan_futsal.model.request.riwayatRequest
import com.opencv.lapangan_futsal.model.request.userRequest
import com.opencv.lapangan_futsal.model.response.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(
        @Body request: userRequest
    ): Call<userResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(
        @Body request: registerRequest
    ): Call<userResponse>

//    @Headers("Content-Type: application/json")
@FormUrlEncoded
@POST("sedekahs")
fun sedekah(
    @Field("idUser") idUser: String,
    @Field("namaSampah") namaSampah: String,
    @Field("foto") foto: RequestBody, // Ubah tipe data parameter menjadi RequestBody
    @Field("opsi") opsi: String,
    @Field("status") status: String,
    @Field("lat") lat: String,
    @Field("lng") lng: String
): Call<sedekahResponse>

    @Headers("Content-Type: application/json")
    @POST("jemputs")
    fun jemput(
        @Body request: jemputRequest
    ): Call<jemputResponse>

    @Headers("Content-Type: application/json")
    @GET("outlites")
    fun outlite(
    ): Call<outliteResponseData>

    @Headers("Content-Type: application/json")
    @GET("cari/outlite/{lat}/{lng}")
    fun cariOutlites(
        @Path("lat") lat:Double,
        @Path("lng") lng:Double,
    ): Call<carioutliteResponseData>

    @Headers("Content-Type: application/json")
    @GET("outlites/{id}")
    fun outliteData(
        @Path("id") id: Int
    ): Call<outliteResponseData>

    @Headers("Content-Type: application/json")
    @POST("outlites")
    fun outlite(
        @Body request: outliteRequest
    ): Call<outliteResponse>

    @Headers("Content-Type: application/json")
    @GET("sedekahs/valid/{id}")
    fun sedekahValid(
        @Path("id") id: String
    ): Call<sedekahResponse>

    @Headers("Content-Type: application/json")
    @GET("sedekahs/segera/{id}")
    fun sedekahSegera(
        @Path("id") id: String
    ): Call<sedekahResponse>

    @Headers("Content-Type: application/json")
    @GET("jemputs/valid/{id}")
    fun jemputValid(
        @Path("id") id: String
    ): Call<sedekahResponse>

    @Headers("Content-Type: application/json")
    @GET("jemput/segera/{id}")
    fun jemputSegera(
        @Path("id") id: String
    ): Call<sedekahResponse>

    @Headers("Content-Type: application/json")
    @POST("riwayats")
    fun riwayat(
        @Body request: riwayatRequest
    ): Call<riwayatResponse>

    @Headers("Content-Type: application/json")
    @GET("pencairan/{id}")
    fun pencairan(
        @Path("id") id: String
    ): Call<riwayatResponse>

    @Headers("Content-Type: application/json")
    @GET("pencairan/valid/{id}")
    fun pencairanValid(
        @Path("id") id: String
    ): Call<riwayatResponse>

    @Headers("Content-Type: application/json")
    @GET("lihat/user")
    fun lihatUser(
    ): Call<userResponseGet>

    @Headers("Content-Type: application/json")
    @GET("ganti/user/role/{id}")
    fun gantiUser(
        @Path("id") id: String
    ): Call<userResponse>

    @Headers("Content-Type: application/json")
    @GET("index/belum/valid")
    fun indexBelumValid(
    ): Call<List<outliteResponseGet>>

    @Headers("Content-Type: application/json")
    @GET("index/sudah/valid/{id}")
    fun indexSudahValid(
        @Path("id") id: Int
    ): Call<List<outliteResponseGet>>

}
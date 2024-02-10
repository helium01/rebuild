package com.opencv.lapangan_futsal.activity.user

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.app.ApiConfig
import com.opencv.lapangan_futsal.app.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

class SedekahActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_sedekah)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val buttonTakePicture = findViewById<Button>(R.id.buttonTakePicture)
        buttonTakePicture.setOnClickListener {
            dispatchTakePictureIntent()
        }

    }


    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap?
            imageBitmap?.let {
                val byteArrayOutputStream = ByteArrayOutputStream()
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
                val imageBody = byteArrayOutputStream.toByteArray()
                val requestBody = imageBody.toRequestBody("image/jpeg".toMediaTypeOrNull())
                val imagePart =
                    MultipartBody.Part.createFormData("foto", "image.jpg", requestBody)

                val idUser = "1"
                val namaSampah = "an organik"
                val opsi = "gatau di isi apa"
                val status = "orapopo"
                val lat = "-7.983908"
                val lng = "112.621391"
                val idUserRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), idUser)
                val namaSampahRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), namaSampah)
                val opsiRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), opsi)
                val statusRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), status)
                val latRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), lat)
                val lngRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), lng)



                uploadDataToApi(idUser, namaSampahRequestBody, imagePart, opsiRequestBody, statusRequestBody, latRequestBody, lngRequestBody)
            }
        }
    }

    private fun uploadDataToApi(
        idUser: String,
        namaSampah: RequestBody,
        foto: MultipartBody.Part?,
        opsi: RequestBody,
        status: RequestBody,
        lat: RequestBody,
        lng: RequestBody
    ) {
        val retro = ApiConfig().retrofitClientInstance().create(ApiService::class.java)
        if (foto != null) {
//            retro.sedekah(idUser, namaSampah, foto, opsi, status, lat, lng)
//                .enqueue(object : retrofit2.Callback<sedekahResponse> {
//                    override fun onResponse(
//                        call: Call<sedekahResponse>,
//                        response: Response<sedekahResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val userResponse = response.body()
//                            val data = userResponse?.data
//                            Toast.makeText(
//                                this@SedekahActivity,
//                                "Berhasil Upload ",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            val intent = Intent(this@SedekahActivity, MainActivity::class.java)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                            startActivity(intent)
//                            finish()
//                        } else {
//                            Log.e("error", "error mas")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<sedekahResponse>, t: Throwable) {
//                        Log.e("error", t.message.toString())
//                    }
//                })
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
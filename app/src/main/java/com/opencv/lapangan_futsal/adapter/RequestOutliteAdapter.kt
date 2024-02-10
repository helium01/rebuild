package com.opencv.lapangan_futsal.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.activity.admin.RequestOutliteActivity
import com.opencv.lapangan_futsal.app.ApiConfig
import com.opencv.lapangan_futsal.app.ApiService
import com.opencv.lapangan_futsal.model.response.outliteResponseGet
import retrofit2.Call
import retrofit2.Response

class RequestOutliteAdapter(context: Context, private val outlets: List<outliteResponseGet>) : ArrayAdapter<outliteResponseGet>(context, 0, outlets) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_request_outlite, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        if (outlets.isEmpty()) {
            // Tampilkan tampilan khusus data kosong
            viewHolder.namaUserTextView.text = "Data Kosong"
            viewHolder.namaOutletTextView.text = ""
            viewHolder.alamatOutletTextView.text = ""
            viewHolder.noHpOutletTextView.text = ""
            viewHolder.noHpOutletTextStatus.text = ""
            viewHolder.buttonLokasi.visibility = View.GONE
            viewHolder.buttonVerifikasi.visibility = View.GONE
        } else {
            val outlet = outlets[position]

            // Set data ke ViewHolder
            viewHolder.namaUserTextView.text = outlet.id_user
            viewHolder.namaOutletTextView.text = outlet.nama_outlite
            viewHolder.alamatOutletTextView.text = outlet.alamat
            viewHolder.noHpOutletTextView.text = outlet.no_hp
            viewHolder.noHpOutletTextStatus.text = outlet.status
            viewHolder.buttonLokasi.visibility = View.VISIBLE
            viewHolder.buttonVerifikasi.visibility = View.VISIBLE

            viewHolder.noHpOutletTextView.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                val url = "https://api.whatsapp.com/send?phone=${outlet.no_hp}"
                i.data = Uri.parse(url)
                context.startActivity(i)
            }

            viewHolder.buttonLokasi.setOnClickListener{
                val gmmIntentUri =
                    Uri.parse("geo:${outlet.lat},${outlet.lng}?z=15&q=${outlet.lat},${outlet.lng}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }

            viewHolder.buttonVerifikasi.setOnClickListener {
                val retro= ApiConfig().retrofitClientInstance().create(ApiService::class.java)
                retro.indexSudahValid(outlet.id).enqueue(object : retrofit2.Callback<List<outliteResponseGet>>{
                    override fun onResponse(call: Call<List<outliteResponseGet>>, response: Response<List<outliteResponseGet>>) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                            // Kembali ke halaman home
                            val intent = Intent(context, RequestOutliteActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                            context.startActivity(intent)

                        } else {
                            // Tangani error jika permintaan tidak berhasil
                            Log.e("error","error mas")
                        }
                    }

                    override fun onFailure(call: Call<List<outliteResponseGet>>, t: Throwable) {
                        Toast.makeText(context, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                        // Kembali ke halaman home
                        // Memanggil metode recreate() pada aktivitas saat ini untuk merefresh halaman
                        (context as Activity).recreate()
                    }

                })
            }
        }

        return view!!
    }

    private class ViewHolder(view: View) {
        val namaUserTextView: TextView = view.findViewById(R.id.textViewNamaUser)
        val namaOutletTextView: TextView = view.findViewById(R.id.textViewNamaOutlite)
        val alamatOutletTextView: TextView = view.findViewById(R.id.textViewNamaAlamat)
        val noHpOutletTextView: TextView = view.findViewById(R.id.textViewNamaNohp)
        val noHpOutletTextStatus: TextView = view.findViewById(R.id.textViewNamaStatus)
        val buttonLokasi: Button = view.findViewById(R.id.buttonLokasi)
        val buttonVerifikasi: Button = view.findViewById(R.id.buttonVerify)
    }
}

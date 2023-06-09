package com.opencv.bank_sampah.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.opencv.bank_sampah.R

class BankSampahAdapter(private val context: Context, private val bankSampahList: List<BankSampah>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return bankSampahList.size
    }

    override fun getItem(position: Int): Any {
        return bankSampahList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_bank_sampah, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val bankSampah = bankSampahList[position]

        viewHolder.namaTextView.text = bankSampah.nama
        viewHolder.pemilikTextView.text = bankSampah.pemilik
        viewHolder.noHpTextView.text = bankSampah.noHp
        viewHolder.alamatTextView.text = bankSampah.alamat

        view.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:${bankSampah.latitude},${bankSampah.longitude}?q=${bankSampah.nama}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val namaTextView: TextView = view.findViewById(R.id.textViewNama)
        val pemilikTextView: TextView = view.findViewById(R.id.textViewPemilik)
        val noHpTextView: TextView = view.findViewById(R.id.textViewNoHp)
        val alamatTextView: TextView = view.findViewById(R.id.textViewAlamat)
    }
}
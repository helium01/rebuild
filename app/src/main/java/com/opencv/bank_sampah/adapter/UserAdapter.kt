package com.opencv.bank_sampah.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.opencv.bank_sampah.R
import com.opencv.bank_sampah.model.data.User
import com.opencv.bank_sampah.model.data.modeluser

class UserAdapter(context: Context, users: List<modeluser>) :
    ArrayAdapter<modeluser>(context, 0, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false)
        }

        val user = getItem(position)

        val nameTextView = view?.findViewById<TextView>(R.id.nameTextView)
        val emailTextView = view?.findViewById<TextView>(R.id.emailTextView)
        val roleTextView=view?.findViewById<TextView>(R.id.roleTextView)

        nameTextView?.text = user?.nama
        emailTextView?.text = user?.email
        roleTextView?.text = user?.role

        return view!!
    }
}
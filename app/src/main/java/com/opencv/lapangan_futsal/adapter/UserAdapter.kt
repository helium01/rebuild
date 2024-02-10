package com.opencv.lapangan_futsal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.opencv.lapangan_futsal.R
import com.opencv.lapangan_futsal.model.response.users

class UserAdapter(private val context: Context, private val userList: List<users>) : BaseAdapter() {

    override fun getCount(): Int {
        return userList.size
    }

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false)
            holder = ViewHolder()
            holder.textViewName = view.findViewById(R.id.textViewName)
            holder.textViewEmail = view.findViewById(R.id.textViewEmail)
            holder.textViewRole = view.findViewById(R.id.textViewRole)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val user = userList[position]
        holder.textViewName?.text = user.name
        holder.textViewEmail?.text = user.email
        holder.textViewRole?.text = user.role

        return view
    }

    private class ViewHolder {
        var textViewName: TextView? = null
        var textViewEmail: TextView? = null
        var textViewRole: TextView? = null
    }
}
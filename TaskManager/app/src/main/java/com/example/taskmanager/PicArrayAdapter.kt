package com.example.taskmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView

class PicArrayAdapter(context: Context, var data: ArrayList<Int>) :
    ArrayAdapter<Int>(context, R.layout.pic_item, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.pic_item, parent, false)
        }
        view!!.findViewById<ImageView>(R.id.itemPic).setImageResource(data[position])
        return view
    }
}
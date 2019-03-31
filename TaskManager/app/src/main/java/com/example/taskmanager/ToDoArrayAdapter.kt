package com.example.taskmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class ToDoArrayAdapter(context: Context, var data: ArrayList<ToDoItem>) :
    ArrayAdapter<ToDoItem>(context, R.layout.to_do_item, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.to_do_item, parent, false)
        }
        view!!.findViewById<TextView>(R.id.desc).text = data[position].desc
        view.findViewById<TextView>(R.id.eventDate).text = data[position].d
        view.findViewById<ImageView>(R.id.img).setImageResource(data[position].imgId)
        return view
    }
}
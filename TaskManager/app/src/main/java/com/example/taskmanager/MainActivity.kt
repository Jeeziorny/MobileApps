package com.example.taskmanager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var events = ArrayList<ToDoItem>()
    lateinit var eventsAdapter : ToDoArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eventsAdapter = ToDoArrayAdapter(this, events)
        eventsList.adapter = eventsAdapter
        eventsList.setOnItemLongClickListener{_, _, idx, _ ->
            fstDialogOnItemClick(idx)
        }
        Log.d("infod", "ONCREATE")
    }

    fun onNewClick(view: View) {
        val myIntent = Intent(this, ScndActivity::class.java)
        startActivityForResult(myIntent, 123)
    }

    fun fstDialogOnItemClick(idx: Int) : Boolean {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Properties")
        builder.setPositiveButton("Done") { _, _ -> itemRemove(idx)}
        builder.setNegativeButton("Edit") { _, _ -> itemEdit(idx)}
        builder.show()
        return true
    }

    fun itemRemove(idx: Int) : Boolean {
        events.removeAt(idx)
        eventsAdapter.notifyDataSetChanged()
        return true
    }

    fun itemEdit(idx: Int) : Boolean {
        val myIntent = Intent(this, ScndActivity::class.java)
        myIntent.putExtra("underEdit", events[idx])
        myIntent.putExtra("index", idx)
        startActivityForResult(myIntent, 321)
        return true
    }

    fun sortByPic(view: View) {
        events.sortWith(compareBy({it.imgId}))
        eventsAdapter.notifyDataSetChanged()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            var temp = savedInstanceState.getParcelableArrayList<ToDoItem>("item")
            if (temp != null) {
                events = temp
                eventsAdapter = ToDoArrayAdapter(this, temp)
                eventsAdapter.notifyDataSetChanged()
                eventsList.adapter = eventsAdapter
                //Log.d("infod", "Odtwarzam ${temp.size} obiektow")

            }
            //Log.d("infod", "nie dziala")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        var items = events
        outState?.putParcelableArrayList("item", items)
        var s = items.size
        //Log.d("infod", "Zapisuje $s obiektow")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val a = data!!.getStringExtra("date")
            val b = data.getStringExtra("description")
            val c = data.getIntExtra("picId", -1)

            events.add(ToDoItem(a, b, c))
            eventsAdapter.notifyDataSetChanged()
        } else if (requestCode == 321) {
            val result = data!!.getParcelableExtra<ToDoItem>("result")
            val index = data.getIntExtra("index", -1)
            Log.d("infod", "Indeks: $index")
            if (index != -1) {
                events[index] = result
                eventsAdapter.notifyDataSetChanged()
            }
        }
    }
}

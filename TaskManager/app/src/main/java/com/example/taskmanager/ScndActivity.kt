package com.example.taskmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.scnd_activity.*


class ScndActivity : AppCompatActivity() {
    var picIds = ArrayList<Int>()
    var currId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        picIds.addAll(listOf(R.drawable.b1, R.drawable.b2, R.drawable.b3,
                             R.drawable.b4, R.drawable.b5))
        setContentView(R.layout.scnd_activity)
        val myPicsAdapter = PicArrayAdapter(this, picIds)
        picList.adapter = myPicsAdapter
        picList.setOnItemClickListener { _, _, idx, _ ->
            currId = picIds[idx]
        }
        if (intent.extras != null) {
           //Log.d("infod", "GOT INTENT")
            var temp: ToDoItem? = intent.extras.getParcelable("underEdit") as ToDoItem
            var idx: Int? = intent.extras.getInt("index")
            if (temp != null && idx != null) {
                //Log.d("infod", "Recive index: $idx")
                scndConfirm.setOnClickListener { editOld(idx) }
                newDate.setText(temp.d)
                newDesc.setText(temp.desc)
            }
        }
    }

    fun addNew(view: View) {
        val myIntent = Intent()
        val d = newDate.text.toString()
        val e = newDesc.text.toString()

        if (e.isBlank() || d.isBlank() || currId == -1) {
            scndTitle.text = resources.getString(R.string.onIncorrectEvent)
        } else {
            myIntent.putExtra("date", d)
            myIntent.putExtra("description", e)
            myIntent.putExtra("picId", currId)

            setResult(Activity.RESULT_OK, myIntent)
            finish()
        }
    }

    fun editOld(idx: Int) {
        val myIntent = Intent()
        val d = newDate.text.toString()
        val e = newDesc.text.toString()

        if (e.isBlank() || d.isBlank() || currId == -1) {
            scndTitle.text = resources.getString(R.string.onIncorrectEvent)
        } else {
            var temp = ToDoItem(d, e, currId)
            myIntent.putExtra("result", temp)
            myIntent.putExtra("index", idx)
            Log.d("infod", "Sending idx = $idx")
            setResult(Activity.RESULT_OK, myIntent)
            finish()
        }

    }
}

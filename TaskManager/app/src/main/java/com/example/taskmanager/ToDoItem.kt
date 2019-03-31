package com.example.taskmanager

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ToDoItem(var d: String,
               var desc: String,
               var imgId: Int) : Parcelable
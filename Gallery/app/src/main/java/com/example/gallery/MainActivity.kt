package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.Menu
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var currentPhotoPath: String = ""
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            val fragment = PicListFragment()
            transaction.add(R.id.mainLayout, fragment)
            transaction.commit()
        }
        //TODO: Uncomment
        //setSupportActionBar(findViewById(R.id.my_toolbar))
    }

    /*
    TODO: Uncomment
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.camera_tab, menu)
        return super.onCreateOptionsMenu(menu)
    }*/

    /*
    TODO: stanelo na tym, ze aplikacja dziala poprawnie.
    Probowales robic zdjecia i je dodawac, udalo Ci sie tylko odpalac
    zewnetrzna aplikacje do robienia zdjec i nic poza tym.

    Cala aplikacja chodzi OK!
     */
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.camera -> {
            Log.d("infod", "Klik!")
            dispatchTakePictureIntent()
            true
        }

        else -> {
            false
        }
    }

    fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
}

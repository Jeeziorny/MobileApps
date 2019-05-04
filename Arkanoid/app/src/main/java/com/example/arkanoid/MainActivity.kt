package com.example.arkanoid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var gView: GameView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gView = GameView(this)
        //setContentView(R.layout.activity_main)
        setContentView(gView)
    }

    override fun onResume() {
        super.onResume()
        gView.resume()
    }

    override fun onPause() {
        super.onPause()
        gView.pause()
    }
}

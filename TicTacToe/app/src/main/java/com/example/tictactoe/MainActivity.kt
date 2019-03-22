package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var buttons: Array<Button>
    var board: Array<Int> = arrayOf(0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0)

    enum class owner{
        USER, BOT, NONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons = arrayOf(b00, b01, b02, b03, b04,
                          b10, b11, b12, b13, b14,
                          b20, b21, b22, b23, b24,
                          b30, b31, b32, b33, b34,
                          b40, b41, b42, b43, b44)
    }

    fun userService(view: View) {
        (view as Button).text = "O"
        view.setOnClickListener(null)
    }
    //TODO:
    // -message after finish,
    // -bot,
    // -
    fun bot() {

    }

    fun isFinished() {
        var Hsum = 0
        var Vsum = 0
        for (i in 0..4) {
            for (j in 0..4) {
                Hsum += board[i*5+j]
                Vsum += board[i + j*5]
            }
            if (Hsum == 5 || Vsum == 5) {
                Log.d("WINNER", "PLAYER")
                //TODO: add functionality
            } else if (Hsum == -5 || Vsum == -5) {
                Log.d("WINNER", "BOT")
            }
            Hsum = 0
            Vsum = 0
        }
        var a = board[0]+board[6]+board[12]+board[18]+board[24]
        var b = board[4]+board[8]+board[12]+board[16]+board[20]
        if (a == 5 || b == 5) {
            Log.d("WINNER", "PLAYER")
        } else if (b == -5 || b == -5) {
            Log.d("WINNER", "BOT")
        }
    }
}

package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var buttons: Array<Button>
    var bd = IntArray(25)
    var sumOnLine = IntArray(12)


    enum class winner {
        USER, BOT, NONE, NOTYET
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
        bd[buttons.indexOf(view)] = 1
        sumUpdate()
        when (isFinished()) {
            winner.USER -> announce(winner.USER)
            winner.BOT -> announce(winner.BOT)
            winner.NONE -> announce(winner.NONE)
            else -> bot()
        }
    }

    fun again(view: View) {
        buttons.forEach { it.setOnClickListener { e -> userService(it) } }
        buttons.forEach { it.text = ""}
        sumOnLine.forEach { e -> 0 }
        for (i in 0..24) {
            bd[i] = 0
        }
        message.text = "Show me what you can!!!"
    }

    fun sumUpdate() {
        var index = 0
        for(i in 0..11) sumOnLine[i] = 0
        for (j in 0..20 step 5) {
            for (i in 0..4) {
                sumOnLine[index] += bd[j + i]
            }
            index++
        }
        for (i in 0..4) {
            for (j in 0..20 step 5) {
                sumOnLine[index] += bd[j + i]
            }
            index++
        }
        sumOnLine[index++] = bd[0]+bd[6]+bd[12]+bd[18]+bd[24]
        sumOnLine[index] = bd[20]+bd[16]+bd[12]+bd[8]+bd[4]
    }

    fun bot() {
        var done = false
        var index = 0
        for (i in 1..11) {
            if (sumOnLine[i] > sumOnLine[index])
                index = i
        }
        if (index < 5) {
            for (i in 0..4) {
                if (bd[index*5+i] == 0) {
                    bd[index*5+i] = -1
                    buttons[index*5+i].text = "X"
                    buttons[index*5+i].setOnClickListener(null)
                    done = true
                    break
                }
            }
        } else if (index < 10) {
            for (i in 0..20 step 5) {
                if (bd[index%5+i] == 0) {
                    bd[index%5+i] = -1
                    buttons[index%5+i].text = "X"
                    buttons[index%5+1].setOnClickListener(null)
                    done = true
                    break
                }
            }
        } else if (index == 10) {
            for (i in 0..24 step 6) {
                if (bd[i] == 0) {
                    bd[i] = -1
                    buttons[i].text = "X"
                    buttons[i].setOnClickListener(null)
                    done = true
                    break
                }
            }
        } else {
            for (i in 4..20 step 4) {
                if (bd[i] == 0) {
                    bd[i] = -1
                    buttons[i].text = "X"
                    buttons[i].setOnClickListener(null)
                    done = true
                    break
                }
            }
        }
         if (!done) {
            for (i in 0..24) {
                if(bd[i] == 0) {
                    bd[i] = -1
                    buttons[i].text = "X"
                    buttons[i].setOnClickListener(null)
                    break
                }
            }
        }
        sumUpdate()
    }

    fun announce(w: winner) {
        when (w) {
            winner.USER -> message.text = "You won! :)"
            winner.BOT  -> message.text = "Bot won :("
            else -> message.text = "nobody won, maybe again?"
        }
    }

    fun isFinished() : winner {
        var isEnd = true
        for(i in bd) {
            if (i == 0) {
                isEnd = false
            }
        }
        if (isEnd)
            return winner.NONE
        for (i in sumOnLine) {
            when(i) {
                5  -> return winner.USER
                -5 -> return winner.BOT
            }
        }
        return winner.NOTYET
    }
}

package com.example.hangman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var wordArray: Array<String>
    var currWord = ""
    var puzzle: String = ""
    var liveCounter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordArray = resources.getStringArray(R.array.words)
        reset()
    }

    fun again(view: View) {
        reset()
    }

    fun reset() {
        puzzle = ""
        liveCounter = 1
        currWord = wordArray.get(Random.nextInt(0, 6)).toUpperCase()
        for (i in currWord)
            puzzle += "_ "
        textView.text = puzzle
        imageView.setImageResource(R.drawable.aa)
    }


    fun btnService(view: View) {
        var input: Char = (view as Button).text[0]
        var isLetterGuessed = false
        var isWordGuessed = true
        Log.d("infod", "INPUT: , CURRWORD: $currWord")
        for (i in 0 until currWord.length) {
            if (currWord[i] == input) {
                puzzle = puzzle.substring(0, 2 * i) + input + puzzle.substring(2 * i + 1)
                textView.text = puzzle
                isLetterGuessed = true
            }
        }
        if(!puzzle.contains("_")) {
            textView.text = "You guessed!"

        }
        if (!isLetterGuessed) {
            liveCounter++
            when (liveCounter) {
                1 -> imageView.setImageResource(R.drawable.aa)
                2 -> imageView.setImageResource(R.drawable.bb)
                3 -> imageView.setImageResource(R.drawable.cc)
                4 -> imageView.setImageResource(R.drawable.dd)
                5 -> imageView.setImageResource(R.drawable.ee)
                6 -> imageView.setImageResource(R.drawable.ff)
                7 -> imageView.setImageResource(R.drawable.gg)
                8 -> imageView.setImageResource(R.drawable.hh)
                9 -> imageView.setImageResource(R.drawable.ii)
                10 -> {
                    imageView.setImageResource(R.drawable.jj)
                    textView.text = "Hangman is dead :("
                }
            }
        }
    }
}

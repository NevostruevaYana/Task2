package com.example.task2

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var se: String = "Seconds elapsed: "
    private lateinit var sp: SharedPreferences
    var f = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        sp = applicationContext.getSharedPreferences("p", MODE_PRIVATE)
        secondsElapsed = sp.getInt("sec", secondsElapsed)
        f = true
        val backgroundThread = Thread{
            while (f) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = se + secondsElapsed++
                }
            }
        }
        backgroundThread.isDaemon = true
        backgroundThread.start()
    }

    override fun onPause() {
        super.onPause()
        f = false
        val e = sp.edit()
        e.putInt("sec", secondsElapsed)
        e.apply()
    }

}
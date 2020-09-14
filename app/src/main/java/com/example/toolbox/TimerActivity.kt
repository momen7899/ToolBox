package com.example.toolbox

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_timer.*


class TimerActivity : AppCompatActivity() {

    private var startTime: Long = 0
    private val customHandler = Handler()
    private var timeInMilliseconds = 0L
    private var timeSwapBuff = 0L
    private var updatedTime = 0L

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)


        timer_play.setOnClickListener { _ ->
            startTime = SystemClock.uptimeMillis()
            customHandler.postDelayed(updateTimerThread, 0)
        }
        timer_stop.setOnClickListener { _ ->
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
        }

        timer_start.setOnClickListener {
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
            startTime = 0
            timeInMilliseconds = 0
            timeSwapBuff = 0
            updatedTime = 0
            timer_time.text = getString(R.string.timerStartTime)
        }
    }

    private val updateTimerThread: Runnable = object : Runnable {
        override fun run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime
            updatedTime = timeSwapBuff + timeInMilliseconds
            var secs = (updatedTime / 1000)
            var min = secs / 60
            secs %= 60
            timer_time.text = (String.format("%02d", min) + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%02d", (updatedTime % 100)))
            customHandler.postDelayed(this, 0)
        }
    }

}
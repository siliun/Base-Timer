package com.test.basetimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private var hour = 0
    private var minute = 0
    private var second = 0
    private var isRunning = false
    private var timerTask: Timer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener {
            println("timer start")
            if(!isRunning){
                isRunning = !isRunning
                startTimer()
            }
        }

        btn_stop.setOnClickListener {
            println("timer stop")
            if(isRunning){
                isRunning = !isRunning
                stopTimer()
            }
        }

        btn_reset.setOnClickListener {
            println("timer reset")
            resetTimer()
        }

    }


    private fun upHour(){
        hour++
    }

    private fun upMinute(){
        minute++
    }

    private fun upSecond(){
        second++
    }

    private fun zeroMinute(){
        minute=0
    }

    private fun zeroSecond(){
        second=0
    }

    private fun resetTimer(){
        hour = 0
        minute = 0
        second = 0
    }

    private fun startTimer(){
        timerTask=timer(period=1000){
            upSecond()
            if(second==60){
                upMinute()
                zeroSecond()
            }
            if(minute==60){
                upHour()
                zeroMinute()
            }
            runOnUiThread {
                if(hour < 10) {
                    txtHour.text = "0$hour"
                }else{
                    txtHour.text = "$hour"
                }
                if(minute < 10) {
                    txtMinute.text = "0$minute"
                } else {
                    txtMinute.text = "$minute"
                }
                if(second < 10) {
                    txtSecond.text = "0$second"
                }else{
                    txtSecond.text = "$second"
                }

            }

        }
    }

    private fun stopTimer(){
        timerTask?.cancel()
    }


}

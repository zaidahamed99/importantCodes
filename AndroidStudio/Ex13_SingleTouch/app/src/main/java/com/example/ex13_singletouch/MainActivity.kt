package com.example.ex13_singletouch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //THIS TIME THE APP WONT BE CONNECTED TO A LAYOUT, BUT OUR OWN CLASS
        setContentView(SingleTouchView(this))
    }
}


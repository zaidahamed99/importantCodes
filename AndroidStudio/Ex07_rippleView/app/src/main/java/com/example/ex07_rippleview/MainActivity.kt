package com.example.ex07_rippleview

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import r21nomi.com.glrippleview.GLRippleView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding
        val glview = findViewById<GLRippleView>(R.id.glView)

        glview.run {
            addBackgroundImages(listOf(
                BitmapFactory.decodeResource(resources, R.drawable.sithakarn),
                BitmapFactory.decodeResource(resources, R.drawable.sithakarn2)
            ))
            setRippleOffset(0.01F)
            setFadeInterval(2000)
            startCrossFadeAnimation()
        }
    }


}
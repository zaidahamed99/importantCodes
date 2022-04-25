package com.example.ex20_photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //object binding
        var photoBtn = findViewById<Button>(R.id.photoBtn)
        var imageView = findViewById<ImageView>(R.id.imageView)

        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            imageView.setImageURI(it)
        }
        //when button is pressed, the app will navigate the user to the device's storage to
        // choose a photo, and then put that photo on the screen
        photoBtn.setOnClickListener {
            loadImage.launch("image/*") //to load image in any format
        }
    }
}
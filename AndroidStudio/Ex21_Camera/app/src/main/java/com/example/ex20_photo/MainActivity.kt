package com.example.ex20_photo

import android.Manifest
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

//give permission to camera (line 5 of android mabnifest)
class MainActivity : AppCompatActivity() {
    //lateinit means that we'll declare the values later
    lateinit var photoBtn: Button
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //object binding
        photoBtn = findViewById(R.id.photoBtn)
        imageView = findViewById(R.id.imageView)
    }

    fun takePhoto(view: View){
        requestCameraPermission.launch(Manifest.permission.CAMERA)  //jumps to the requestCameraPermission function
    }

    //take camera permission
    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isSuccess: Boolean ->
        if (isSuccess){
            Log.d("Take picture", "Permission granted")
            takePicture.launch(null)
        } else {
            Toast.makeText(applicationContext, "Camera has no permission", Toast.LENGTH_SHORT).show()
        }
    }

    //This function will compress the image
    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){ bitmap: Bitmap ->
        Log.d("Take picture", "Show Bitmap Pictures")
        imageView.setImageBitmap(bitmap)

    }
}
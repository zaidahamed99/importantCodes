package com.example.ex20_photo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class MainActivity : AppCompatActivity() {

    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null
    lateinit var imageView: ImageView
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //object binding
        var photoBtn = findViewById<Button>(R.id.photoBtn)
        var uploadBtn = findViewById<Button>(R.id.uploadButton)
        imageView = findViewById(R.id.imageView)


        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri ->
            imageView.setImageURI(uri)
            filePath = uri
            Log.d("Photo Location", uri.toString())
        }

//  OR

//        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent()){
//            imageView.setImageURI(it)
//        }

        //when button is pressed, the app will navigate the user to the device's storage to
        // choose a photo, and then put that photo on the screen
        photoBtn.setOnClickListener {
            loadImage.launch("image/*") //to load image in any format
        }

        //Upload this picture to firebase
        uploadBtn.setOnClickListener {
            if (filePath != null){
                Toast.makeText(applicationContext, "Uploading... photo... ", Toast.LENGTH_SHORT).show()
                val imageRef = storageReference!!.child("image/"+ UUID.randomUUID().toString())
                imageRef.putFile(filePath!!)
                    .addOnSuccessListener{
                        Toast.makeText(applicationContext, "File uploaded", Toast.LENGTH_SHORT).show()
                }
                    .addOnFailureListener{
                        Toast.makeText(applicationContext, "Failed to upload photo", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener{ taskSnapshot ->
                        val progress = 100.0 + taskSnapshot.bytesTransferred/taskSnapshot.totalByteCount
                        Toast.makeText(applicationContext, "Uploaded ${progress.toInt()} %", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

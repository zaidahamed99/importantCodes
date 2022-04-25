package com.example.ex04_customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val courseNoTextView = findViewById<TextView>(R.id.courseNoTextView)
        val creditsTextView = findViewById<TextView>(R.id.creditsTextView)
        val descTextView = findViewById<TextView>(R.id.descTextView)
        val image = findViewById<ImageView>(R.id.imageCourse)

        val title = intent.getStringExtra("courseTitle")
        titleTextView.text = title.toString()

        val desc = intent.getStringExtra("courseDes")
        descTextView.text = desc.toString()

        val courseNo = intent.getIntExtra("courseNo", 0)
        courseNoTextView.text = courseNo.toString()

        val credits = intent.getDoubleExtra("courseCredits", 0.0)
        creditsTextView.text = credits.toString()

        val position = intent.getIntExtra("position", 0)

        val imagePos = position%3+1
        val myImage = resources.getIdentifier("image1010"+imagePos, "drawable", packageName)
        image.setImageResource(myImage)




    }
}
  package com.example.ex08_json

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.library.bubbleview.BubbleTextView
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

  class MainActivity : AppCompatActivity() {

      //calling the firebase

      private val jsonURL = arrayOf("https://egco428-json.firebaseio.com/movies/1.json",
      "https://egco428-json.firebaseio.com/movies/2.json",
      "https://egco428-json.firebaseio.com/movies/3.json",
      "https://egco428-json.firebaseio.com/movies/4.json",
      "https://egco428-json.firebaseio.com/movies/5.json")


      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)

          val button = findViewById<Button>(R.id.button)
          val mvNameBTextView = findViewById<BubbleTextView>(R.id.mvNameBTextView)
          val mvNameBTextView2 = findViewById<BubbleTextView>(R.id.mvNameBTextView2)
          val mvNameBTextView3 = findViewById<BubbleTextView>(R.id.mvNameBTextView3)
          val mvNameBTextView4 = findViewById<BubbleTextView>(R.id.mvNameBTextView4)
          val movieTitle = findViewById<TextView>(R.id.movieTitle)

          button.setOnClickListener {
              val client = OkHttpClient()
              var asyncTask = object :AsyncTask<String, String, String>(){
                  override fun onPreExecute() {
                      Toast.makeText(this@MainActivity, "Please wait for the movie detail", Toast.LENGTH_SHORT).show()
                  }

                  override fun doInBackground(vararg arg: String?): String {
                      val builder = Request.Builder()
                      builder.url(arg[0].toString())
                      val request = builder.build()
                      try {
                          val response = client.newCall(request).execute()
                          return response.body!!.string()
                      } catch (e: Exception){
                          e.printStackTrace()
                      }
                      return "{name:''}"
                  }

                  override fun onPostExecute(result: String?) {
                      val movieText = Gson().fromJson(result, Movie::class.java)
                      movieTitle.text = movieText.name
                      mvNameBTextView.text = movieText.name
                      mvNameBTextView2.text = movieText.Star
                      mvNameBTextView3.text = movieText.Year.toString()
                      mvNameBTextView4.text = movieText.Genre

                  }

              }
              val rnds = (0..4).random()
              asyncTask.execute(jsonURL[rnds])
          }

      }
  }
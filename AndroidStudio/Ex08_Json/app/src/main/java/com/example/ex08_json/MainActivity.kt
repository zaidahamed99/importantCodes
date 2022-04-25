  package com.example.ex08_json

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.github.library.bubbleview.BubbleTextView
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

  class MainActivity : AppCompatActivity() {

      //calling the firebase
      private val jsonURL: String = "https://egco428-json.firebaseio.com/movies/1.json"

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)

          val button = findViewById<Button>(R.id.button)
          val mvNameBTextView = findViewById<BubbleTextView>(R.id.mvNameBTextView)

          button.setOnClickListener {
              val client = OkHttpClient()
              var asyncTask = object :AsyncTask<String, String, String>(){
                  override fun onPreExecute() {
                      Toast.makeText(this@MainActivity, "Please wait for the message return", Toast.LENGTH_SHORT).show()
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
                      mvNameBTextView.text = movieText.name
                  }

              }
              asyncTask.execute(jsonURL)
          }

      }
  }
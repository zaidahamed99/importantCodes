package com.example.ex09_rss

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ex09_rss.Adapter.FeedAdapter
import com.example.ex09_rss.Model.RSSObject
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Okio
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private val RSS_link = "http://rss.nytimes.com/services/xml/rss/nyt/Science.xml"
    private val RSS_to_JSON_API = "https://api.rss2json.com/v1/api.json?rss_url="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bringing the xml element into kotlin
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //same like setting up an adapter
        val linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false
        )
        recyclerView.layoutManager = linearLayoutManager

        //Function to call data from Json
        loadRSS(recyclerView)
    }

    private fun loadRSS(recyclerVw: RecyclerView) {
        val client =OkHttpClient()
        val loadRSSAsync = object:AsyncTask<String, String, String>(){
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
                var rssObject: RSSObject
                rssObject = Gson().fromJson(result, RSSObject::class.java)
                var adapter = FeedAdapter(rssObject, baseContext)
                recyclerVw.adapter = adapter
                adapter.notifyDataSetChanged()

            }

        }
        val url_get_data = StringBuilder(RSS_to_JSON_API)
        url_get_data.append(RSS_link)
        loadRSSAsync.execute(url_get_data.toString())
    }
}

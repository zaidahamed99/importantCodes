package com.example.ex2_lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    val TAG = "DetailActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d(TAG,"onCreate")
        val outputText = findViewById<TextView>(R.id.outputText)
        val bundle = intent.extras
        var result = bundle!!.getString("outputText")

        val sendBack = findViewById<EditText>(R.id.sendBack)
        val sendBackButton = findViewById<Button>(R.id.sendBackButton)
        outputText.setText(result)

        //SENDING TEXT BACK TO MAIN ACTIVITY
        sendBackButton.setOnClickListener {
            Log.d("Check Data", sendBack.text.toString())
            var intent2 = Intent(this, MainActivity::class.java)
            intent2.putExtra("sendBack", sendBack.text.toString())
            startActivity(intent2)
        }
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onRestart(){
        super.onRestart()
        Log.d(TAG,"onRestart")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }
}
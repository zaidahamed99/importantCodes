package com.example.ex2_lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    //val and var has a difference that val's value can never be chaged but var's value can

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate")
        //if it shows Log in red colour, thats because there is no import, press option and enter
        //d is for debug, e is for error
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.text1)
        val text4 = findViewById<EditText>(R.id.text4)

        //CREATING VARIABLE TO RECEIVE THE TEXT FROM DETAIL ACTIVITY
//        val sendBack = findViewById<TextView>(R.id.sendBack)

        val bundle  = intent.extras
        if (bundle!=null){
            var result = bundle!!.getString("sendBack")
            text4.setText(result)
        }

        //You do this everytime you have to bring something from the XML file from Kotlin
        button.setOnClickListener {
            Log.d("Check Data", editText.text.toString())
            var intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("outputText", editText.text.toString())
            startActivity(intent)
            //val output = text1.text.toString() + " " + text4.text.toString()
            //textView.text = output
//            textView.text = text1.text.toString() + " " + text4.text.toString()

        }
        //We are trying to open the 'detail activity page' when the user clicks on the button
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
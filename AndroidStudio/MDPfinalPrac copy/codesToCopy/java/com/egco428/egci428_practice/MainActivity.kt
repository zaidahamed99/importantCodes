package com.egco428.egci428_practice


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.egco428.egci428_practice.Helper.HTTPHelper
import com.egco428.egci428_practice.model.User
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var uname: String? = null
    var pname: String? = null
    var userprofile: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding object

        signUpBtn.setOnClickListener {
            val intent = //..................
            startActivity(intent)
        }
        signInBtn.setOnClickListener {
            uname = userText.text.toString()
            pname = passText.text.toString()
            if (!uname.isNullOrEmpty() && !pname.isNullOrEmpty()) {
                
                //add your code here

            }
        }
        cancelBtn.setOnClickListener {
            userText.text = null
            passText.text = null


        }
    }

}

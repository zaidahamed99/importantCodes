package com.example.finalprac

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprac.model.User
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    var uname: String? = null
    var pname: String? = null
    var userprofile: User? = null
    private val file = "users.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding object
        var userText = findViewById<EditText>(R.id.userText)
        var passText = findViewById<EditText>(R.id.passText)
        var signInBtn = findViewById<Button>(R.id.signInBtn)
        var cancelBtn = findViewById<Button>(R.id.cancelBtn)
        var signUpBtn = findViewById<Button>(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        signInBtn.setOnClickListener {
            uname = userText.text.toString()
            pname = passText.text.toString()
            if (!uname.isNullOrEmpty() && !pname.isNullOrEmpty()) {

                //add your code here
                try {
                    val fIn = openFileInput(file)
                    val mFile = InputStreamReader(fIn)
                    val br = BufferedReader(mFile)
                    var line = br.readLine()
                    val all = StringBuilder()
                    while (line != null){
                        Log.d("Show Line", line)
                        var userItem = line.split(",")
                        if (userItem[0] == uname && userItem[1] == pname){
                            Toast.makeText(baseContext, "Login success", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, UserListActivity::class.java)
                            startActivity(intent)
                        }
                        line = br.readLine()
                    }
                    br.close()
                    mFile.close()
                    fIn.close()

                    Toast.makeText(baseContext, "Login fail", Toast.LENGTH_SHORT).show()

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        cancelBtn.setOnClickListener {
            userText.text = null
            passText.text = null
        }
    }

}
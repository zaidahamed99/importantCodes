package com.example.finalprac

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SignUpActivity : AppCompatActivity() {


    //lateinit var usrList: MutableList<User>
    lateinit var latText: EditText
    lateinit var lonText: EditText
    lateinit var userSignText: EditText
    lateinit var passSignText: EditText

    private val file = "users.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        latText = findViewById(R.id.latText)
        lonText = findViewById(R.id.lonText)
        userSignText = findViewById(R.id.userSignText)
        passSignText = findViewById(R.id.passSignText)

        var randomBtn = findViewById<Button>(R.id.randomBtn)
        var addBtn = findViewById<Button>(R.id.addBtn)



        randomBtn.setOnClickListener {
            latText.setText(randomLocation(true).toString())
                lonText.setText(randomLocation(false).toString())
        }
        addBtn.setOnClickListener {
            saveData()
            val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }


    }

    private fun randomLocation(type: Boolean): Double {
        val r = Random()
        var result: Double
        //true: latitude, false: longitude
        if (type) {
            result = (r.nextInt(170) - 85).toDouble()
        } else {
            result = (r.nextInt(360) - 180).toDouble()
        }
        return result
    }

    private fun saveData() {
        val usr = userSignText.text.toString()
        val pwd = passSignText.text.toString()
        val lat = latText.text.toString().toDouble()
        val lon = lonText.text.toString().toDouble()
        Log.d("Debug", usr)
        if (usr.isEmpty()) {
            userSignText.error = "Please enter a username"
            return
        }

        val userData = usr+","+pwd+","+lat+","+lon+"\n"

        try {
            val fOut = openFileOutput(file, Context.MODE_APPEND)
            fOut.write(userData.toByteArray())
            fOut.close()
            Toast.makeText(applicationContext, "File saved successfully", Toast.LENGTH_SHORT)

        } catch (e: Exception){
            e.printStackTrace()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_signup, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
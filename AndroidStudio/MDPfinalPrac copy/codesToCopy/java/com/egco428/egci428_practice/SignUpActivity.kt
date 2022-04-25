package com.egco428.egci428_practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.egco428.egci428_practice.model.User
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class SignUpActivity : AppCompatActivity() {


    //lateinit var usrList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        

        randomBtn.setOnClickListener {
            latText.setText(//...........)
            lonText.setText(//...........)
        }
        addBtn.setOnClickListener {
            //...............
            val intent = //............................
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

        val userId = //..............
        val userData = //..................
        

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

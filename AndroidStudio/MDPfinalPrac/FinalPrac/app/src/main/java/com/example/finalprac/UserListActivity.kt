package com.example.finalprac

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprac.model.User
import com.example.finalprac.model.UserAdapter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception


class UserListActivity : AppCompatActivity() {

    lateinit var userList: MutableList<User>
    private val file = "users.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        userList = mutableListOf()
        var listView = findViewById<ListView>(R.id.listView)

        userList = mutableListOf()

        //showing all users in the listView
        try {
            val fIn = openFileInput(file)
            val mFile = InputStreamReader(fIn)
            val br = BufferedReader(mFile)
            var line = br.readLine()
            var user = User()

            while (line != null){
                var userItem = line.split(",")
                user = User(userItem[0], userItem[1], userItem[2].toDouble(), userItem[3].toDouble())

                userList.add(user)
                line = br.readLine()
            }
            br.close()
            mFile.close()
            fIn.close()

        }catch (e:Exception){
            e.printStackTrace()
        }

        val adapter = UserAdapter(this, R.layout.userlist, userList)
        listView.adapter = adapter


        listView.setOnItemClickListener { parent, view, position, id ->

            val auser = userList[position]
            Log.d("Show user", auser.username+","+auser.latitude+","+auser.longitude)
                displayMap(auser)
        }
    }

    private fun displayMap(ausr: User) {
        val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("selUser", ausr.username)
            intent.putExtra("selLat", ausr.latitude)
            intent.putExtra("selLon", ausr.longitude)
            startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_userlist, menu)
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
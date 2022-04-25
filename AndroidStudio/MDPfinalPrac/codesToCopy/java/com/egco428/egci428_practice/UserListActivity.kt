package com.egco428.egci428_practice


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.egco428.egci428_practice.model.User
import com.egco428.egci428_practice.model.UserAdapter
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity() {

    lateinit var userList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        userList = mutableListOf()
        

        listView.setOnItemClickListener { parent, view, position, id ->

            val auser = //..............
            displayMap(//...........)
        }
    }

    private fun displayMap(ausr: User) {
        val intent = Intent(this, //...............)
        intent.putExtra("selUser", //...............)
        intent.putExtra("selLat", //................)
        intent.putExtra("selLon", //................)
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

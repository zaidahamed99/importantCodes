package com.example.ex03_listview

import android.app.ListActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

class MainActivity : ListActivity() {
    var wordList = arrayOf("ant", "bat", "cat", "dog")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //
        val adaptor = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, wordList)
        listAdapter = adaptor

    }
}
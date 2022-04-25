package com.example.ex06_searchdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //STEP1 - add all the elements that you added in the XML file
        var searchBtn = findViewById<Button>(R.id.searchBtn)

        searchBtn.setOnClickListener{
            SimpleSearchDialogCompat(
                this@MainActivity, "Search", "Please select one item", null, initData(),
                SearchResultListener{dialog, item, position ->
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                    dialog.dismiss() //this closes the dialog after sometime, if you dont add this, the dialog will just stay there
                }
            ).show()
        }

    }

    private fun initData(): ArrayList<SearchModel>{

        //initializing the arrayList
        val items = ArrayList<SearchModel>()

        //adding items to arrayList
        items.add(SearchModel("Thailand"))
        items.add(SearchModel("Japan"))
        items.add(SearchModel("USA"))
        items.add(SearchModel("Singapore"))
        items.add(SearchModel("England"))

        return items
    }
}
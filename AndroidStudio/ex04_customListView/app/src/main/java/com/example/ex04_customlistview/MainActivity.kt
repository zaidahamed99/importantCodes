package com.example.ex04_customlistview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
//import java.security.AccessControlContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //CREATING VARIABLE FOR THE MAIN LIST VIEW
        val mainListView = findViewById<ListView>(R.id.mainListView)

        //EVERYTIME YOU HAVE TO UPDATE OR CREATE OR EDIT THE LISTVIEW, YOU HAVE TO USE ADAPTOR
        mainListView.adapter = myCustomAdaptor(this)
        mainListView.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position)
            Toast.makeText(this, "${item} at ${id}", Toast.LENGTH_LONG).show()
        }
    }
    //The context thing is important, suppose if we're having a list view, so the data will be under the listview
    // and the listview will be under the 'context'
    private class myCustomAdaptor(context: Context): BaseAdapter(){
        private val names = arrayListOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        private val mContext: Context = context

        override fun getCount(): Int {
            return names.size*7
        }

        override fun getItem(position: Int): Any {
            return names[position%7]
        }

        override fun getItemId(position: Int): Long {
            return (position+1).toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(mContext)
//            val whiteColor = Color.parseColor("#FFFFFF")
//            val grayColor = Color.parseColor("#E0E0E0")
            val monColor = Color.parseColor("#FAF2D5")
            val tuesColor = Color.parseColor("#F3EEF6")
            val wedColor = Color.parseColor("#DDEDE0")
            val thursColor = Color.parseColor("#F8EBD5")
            val friColor = Color.parseColor("#DCE9F6")
            val satColor = Color.parseColor("#E3DAEC")
            val sunColor = Color.parseColor("#EAD9D6")

            //THIS MEANS THAT I'LL HAVE A TEXTVIEW IN MCONTEXT
            textView.text = names[position%7]
            //YOU DONT NEED A FOR LOOP HERE, IT AUTOMATICALLY LOOPS
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row,viewGroup, false)

            val nameTextView = row.findViewById<TextView>(R.id.nametextView)
            val postTextView = row.findViewById<TextView>(R.id.posttextView)

            nameTextView.text = names[position%7]
            postTextView.text = "Row no.: $position"


            if (position%7 == 0){
                row.setBackgroundColor(monColor)
            }
            else if (position%7 == 1) {
                row.setBackgroundColor(tuesColor)
            }
            else if (position%7 == 2) {
                row.setBackgroundColor(wedColor)
            }
            else if (position%7 == 3) {
                row.setBackgroundColor(thursColor)
            }
            else if (position%7 == 4) {
                row.setBackgroundColor(friColor)
            }
            else if (position%7 == 5) {
                row.setBackgroundColor(satColor)
            }
            else{
                row.setBackgroundColor(sunColor)
            }

            return row
        }
        //THESE GET FUNCTIONS ARE JUST THE GETTERS THAT WE STUDIED IN JAVA


    }
}
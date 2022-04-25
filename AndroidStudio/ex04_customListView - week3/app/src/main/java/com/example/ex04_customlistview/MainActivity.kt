package com.example.ex04_customlistview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

//import java.security.AccessControlContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //CREATING VARIABLE FOR THE MAIN LIST VIEW
        val mainListView = findViewById<ListView>(R.id.mainListView)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val adapter = myCustomAdaptor()
        val newText = findViewById<EditText>(R.id.inputTextView)


        //EVERYTIME YOU HAVE TO UPDATE OR CREATE OR EDIT THE LISTVIEW, YOU HAVE TO USE ADAPTOR
        mainListView.adapter = adapter

        addBtn.setOnClickListener{
        adapter.insertItem(newText.text.toString())
        }


//        mainListView.setOnItemClickListener { adapterView, view, position, id ->
//            val item = adapterView.getItemAtPosition(position)
//            Toast.makeText(this, "${item} at ${id}", Toast.LENGTH_LONG).show()
//        }
    }
    //The context thing is important, suppose if we're having a list view, so the data will be under the listview
    // and the listview will be under the 'context'
    private class myCustomAdaptor(): BaseAdapter(){
        private val names = arrayListOf<String>("Bob", "Tom", "Susan", "John", "Kenny", "Ann", "Betty")
        //private val names = arrayListOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        override fun getCount(): Int {
            return names.size
        }

        override fun getItem(position: Int): Any {
            return names[position]
        }

        fun insertItem(aName: String) {
            names.add(aName)
            notifyDataSetChanged()
        }

        override fun getItemId(position: Int): Long {
            return (position+1).toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val row: (View)

            val whiteColor = Color.parseColor("#FFFFFF")
            val grayColor = Color.parseColor("#E0E0E0")

            //Prepare the rows by holding the viewholder as template
            if (convertView == null){
                val layoutInflater = LayoutInflater.from(viewGroup!!.context)
                row = layoutInflater.inflate(R.layout.row,viewGroup, false)
                val nameTextView = row.findViewById<TextView>(R.id.nametextView)
                val postTextView = row.findViewById<TextView>(R.id.posttextView)
                val viewHolder = ViewHolder(nameTextView, postTextView)
                row.tag = viewHolder

            } else {
                row = convertView
            }

            //Load the values of each row
            val viewHolder = row.tag as ViewHolder
            //ViewHolder here and ViewHolder above are different - they are in different blocks
            viewHolder.nameText.text = names[position]
            viewHolder.postText.text = "Row no.: $position"

            if (position%2==0){
                row.setBackgroundColor(whiteColor)
            }
            else row.setBackgroundColor(grayColor)

            //Deleting a row when clicked on it
            row.setOnClickListener{
                row.animate().setDuration(1500).alpha(0F).withEndAction(Runnable {
                    names.removeAt(position)
                    notifyDataSetChanged() //When this command is called, the name array is updated
                    row.alpha = 1F
                })
            }
            return row
        }
        private class ViewHolder (val nameText: TextView, val postText: TextView){

        }
    }
}
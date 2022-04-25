package com.example.ex04_customlistview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import java.sql.RowId

//import java.security.AccessControlContext

class MainActivity : AppCompatActivity() {
    protected var data: ArrayList<Course>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataProviders.getData()
        var courseArrayAdaptor = CourseArrayAdaptor(this, 0, data!!)

        //CREATING VARIABLE FOR THE MAIN LIST VIEW
        val mainListView = findViewById<ListView>(R.id.mainListView)

        //EVERYTIME YOU HAVE TO UPDATE OR CREATE OR EDIT THE LISTVIEW, YOU HAVE TO USE ADAPTOR
        mainListView.adapter = courseArrayAdaptor

        mainListView.setOnItemClickListener { adapterView, view, position, id ->
            val course = data!![position]
            displayDetail(course, position)
        }
    }

    //EVERYTIME YOU HAVE TO OPEN A NEW PAGE, YOU USE INTENT
    private fun displayDetail(course: Course, rowId: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("courseTitle", course.title)
        intent.putExtra("courseDes", course.description)
        intent.putExtra("courseNo", course.courseNo)
        intent.putExtra("courseCredits", course.credits)
        intent.putExtra("position", rowId)
        startActivity(intent)
    }

    //The context thing is important, suppose if we're having a list view, so the data will be under the listview
    // and the listview will be under the 'context'
    private class CourseArrayAdaptor(var context: Context, resource: Int, var objects: ArrayList<Course>): BaseAdapter(){
        //private val names = arrayListOf<String>("Bob", "Tom", "Susan", "John", "Kenny", "Ann", "Betty")
        //private val names = arrayListOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        override fun getCount(): Int {
            return objects.size
        }

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return (position+1).toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val row: (View)
            val course = objects[position]

            val whiteColor = Color.parseColor("#FFFFFF")
            val grayColor = Color.parseColor("#E0E0E0")

            //Prepare the rows by holding the viewholder as template
            if (convertView == null){
                val layoutInflater = LayoutInflater.from(viewGroup!!.context)
                row = layoutInflater.inflate(R.layout.row,viewGroup, false)
                val nameTextView = row.findViewById<TextView>(R.id.titleTextView)
                val imageView = row.findViewById<ImageView >(R.id.imageView)
                val viewHolder = ViewHolder(nameTextView, imageView)
                row.tag = viewHolder

            } else {
                row = convertView
            }


            //Load the values of each row
            val viewHolder = row.tag as ViewHolder
            //ViewHolder here and ViewHolder above are different - they are in different blocks
            viewHolder.nameText.text = course.title
            val imagePos = position%3+1
            val res = context.resources.getIdentifier("image1010"+imagePos, "drawable", context.packageName)
            viewHolder.imageView.setImageResource(res)

            if (position%2==0){
                row.setBackgroundColor(whiteColor)
            }
            else row.setBackgroundColor(grayColor)


            return row
        }
        private class ViewHolder (val nameText: TextView, val imageView: ImageView){

        }
    }
}

//line 186
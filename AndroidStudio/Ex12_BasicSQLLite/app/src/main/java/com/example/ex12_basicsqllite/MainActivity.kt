package com.example.ex12_basicsqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var dataSource: CommentDataSource? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val commentText = findViewById<EditText>(R.id.commentTextView)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        val idInputText = findViewById<EditText>(R.id.idInputText)

        var comment: Comment

        dataSource = CommentDataSource(this)
        dataSource!!.open()

        val values = dataSource!!.allComments //read all record from Comment Table

        val adapter = ArrayAdapter<Comment>(this, android.R.layout.simple_expandable_list_item_1, values)
        listView.adapter = adapter //means when adapter changes, the listview changes too

        addBtn.setOnClickListener {
            comment = dataSource!!.createComment(commentText.text.toString())
            adapter.add(comment)
            adapter.notifyDataSetChanged()
        }

        deleteBtn.setOnClickListener {
            if (adapter.count > 0){
                comment = adapter.getItem(idInputText.text.toString().toInt()) as Comment
                dataSource!!.deleteComment(comment)
                adapter.remove(comment)
                adapter.notifyDataSetChanged()
            }
        }
        listView.setOnItemClickListener { adapterView, view, position, id ->
            if (adapter.count > 0){

                comment = adapter.getItem(position) as Comment
                dataSource!!.deleteComment(comment)
                adapter.remove(comment)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Message: \""+comment.message+"\" was deleted", Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun onResume() {
        super.onResume()
        dataSource!!.open()
    }

    override fun onPause() {
        super.onPause()
        dataSource!!.close()
    }
}
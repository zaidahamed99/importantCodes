package com.example.ex23_firestore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var dataReference: FirebaseFirestore
    lateinit var editText: EditText
    lateinit var submitBtn: Button
    lateinit var ratingBar: RatingBar
    lateinit var msgList: MutableList<Message>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataReference = FirebaseFirestore.getInstance()

        editText = findViewById(R.id.editText)
        submitBtn = findViewById(R.id.submitBtn)
        ratingBar = findViewById(R.id.ratingBar)

        msgList = mutableListOf()
        listView = findViewById(R.id.mainListView)

        submitBtn.setOnClickListener {
            submitData()
        }

        readFirestoreData()
    }

    //Writing data in the database
    private fun submitData() {
        val msg = editText.text.toString()
        if (msg.isEmpty()){
            editText.error = "Please submit a message"
            return
        }

        //var db = dataReference.collection("data message")
        var db = dataReference.collection("data message")
        val messageId = db.document().id
        val messageData = Message(messageId, msg, ratingBar.rating.toInt(), System.currentTimeMillis().toString())

        db.add(messageData)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Message saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Fail to save message", Toast.LENGTH_SHORT).show()
            }
    }

    //Reading data from the database
    private fun readFirestoreData() {
        var db = dataReference.collection("data message")

        db.orderBy("timeStamp").get()
            .addOnSuccessListener { snapshot ->
                if (snapshot != null){
                    msgList.clear()
                    val messages = snapshot.toObjects(Message::class.java)
                    for (message in messages){
                        msgList.add(message)
                    }
                    val adapter = MessageAdapter(applicationContext, R.layout.row, msgList)
                    listView.adapter = adapter
                    Log.d("Firestore Read successful", messages.toString())

                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Fail to read message from Firestore", Toast.LENGTH_SHORT).show()
            }
    }
}
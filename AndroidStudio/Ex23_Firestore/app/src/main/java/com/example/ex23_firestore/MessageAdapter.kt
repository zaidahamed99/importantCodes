package com.example.ex23_firestore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MessageAdapter(val mContext: Context, val layoutResId: Int, val messageList: List<Message>): ArrayAdapter<Message>(mContext, layoutResId, messageList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(layoutResId, null)

        val msgText = view.findViewById<TextView>(R.id.messageTxt)
        val ratingTxt = view.findViewById<TextView>(R.id.ratingTxt)
        val idTxt = view.findViewById<TextView>(R.id.idtxt)

        msgText.text = "Message: " + messageList[position].message
        ratingTxt.text = "Rating value: " + messageList[position].rating.toString()
        idTxt.text = "ID: " + messageList[position].id

        return view
    }
}
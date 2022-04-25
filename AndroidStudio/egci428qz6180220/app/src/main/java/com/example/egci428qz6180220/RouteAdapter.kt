package com.example.egci428qz6180220

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.egci428qz6180220.Models.ARoute

class RouteAdapter(val mContext: Context, val layoutResId: Int, val routeList: ArrayList<ARoute>):
    ArrayAdapter<ARoute>(mContext, layoutResId, routeList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        /* (4.1) Prepare the layout for each row in the list adapter*/
        val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflator.inflate(layoutResId, null)
        val routeTextView = view.findViewById<TextView>(R.id.routeVw)

        /* (4.2) Add value to each row in the list adapter */
        val rt = routeList[position]
            routeTextView.text = rt.name
                return view
    }
}
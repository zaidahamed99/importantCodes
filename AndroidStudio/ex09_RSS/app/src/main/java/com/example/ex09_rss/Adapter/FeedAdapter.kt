package com.example.ex09_rss.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ex09_rss.Model.RSSObject
import com.example.ex09_rss.R

class FeedAdapter(private val rssObject: RSSObject, private val mContext: Context): RecyclerView.Adapter<FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {

        val itemView = inflater.inflate(R.layout.row, parent, false)
        return FeedViewHolder(itemView)
    }

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(mContext)
    }


    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.txtTitle.text = rssObject.items[position].title
        holder.txtContent.text = rssObject.items[position].content
        holder.txtPubDate.text = rssObject.items[position].pubDate



    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }
}

class FeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
{
    var txtTitle: TextView
    var txtPubDate:TextView
    var txtContent:TextView

    init {

        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtPubDate = itemView.findViewById(R.id.txtPubDate)
        txtContent = itemView.findViewById(R.id.txtContent)
    }
}



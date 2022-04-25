package com.example.ex09_rss.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ex09_rss.Model.Movie
import com.example.ex09_rss.R

class MovieAdapter(private val movie: List<Movie>, private val mContext: Context): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val itemView = inflater.inflate(R.layout.row, parent, false)
        return MovieViewHolder(itemView)
    }

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(mContext)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        holder.txtTitle.text = rssObject.items[position].title
//        holder.txtContent.text = rssObject.items[position].content
//        holder.txtPubDate.text = rssObject.items[position].pubDate

        holder.txtTitle.text = movie[position].name
        holder.txtContent.text = movie[position].Star
        holder.txtPubDate.text = movie[position].Year.toString()



    }

    override fun getItemCount(): Int {
        return movie.size
    }
}

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    var txtTitle: TextView
    var txtPubDate: TextView
    var txtContent: TextView

    init {

        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtPubDate = itemView.findViewById(R.id.txtPubDate)
        txtContent = itemView.findViewById(R.id.txtContent)
    }
}
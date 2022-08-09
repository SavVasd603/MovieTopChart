package com.example.movietopchart

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietopchart.Model.Movie
import com.example.movietopchart.Model.Result
import com.example.movietopchart.ui.MovieActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val movie: MutableList<Result>?):
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val image: ImageView = itemView.image_movie
            val txt_name: TextView = itemView.txt_name
            val txt_createdby: TextView = itemView.txt_createdby
            val card_view: CardView = itemView.cardView


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ItemsViewModel = movie?.get(position)
        holder.txt_name.text = ItemsViewModel?.title
        holder.txt_createdby.text = ItemsViewModel?.original_title
    }

    override fun getItemCount(): Int {
        return movie!!.size
    }
}

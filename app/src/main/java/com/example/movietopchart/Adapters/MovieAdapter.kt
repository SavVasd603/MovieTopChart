package com.example.movietopchart.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.movietopchart.Model.Movie.Result
import com.example.movietopchart.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val movie: MutableList<Result>?, val mItemClickListener: ItemClickListener):
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(id: Int)
    }

        inner class MyViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
            val image: ImageView = ItemView.image_movie
            val txt_rating: TextView = ItemView.tvRating
            val cardView: CardView = ItemView.cardView

            init {
                cardView.setOnClickListener {
                    movie?.get(position)?.id?.let { it -> mItemClickListener.onItemClick(it) }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ItemsViewModel = movie?.get(position)
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie?.get(position)?.poster_path).into(holder.image)
        holder.txt_rating.text = ItemsViewModel?.vote_average.toString()
    }

    override fun getItemCount(): Int {
        return movie!!.size
    }

}

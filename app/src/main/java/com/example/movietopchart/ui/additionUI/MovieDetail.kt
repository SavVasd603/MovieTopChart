package com.example.movietopchart.ui.additionUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import com.example.movietopchart.Api.ApiInterface
import com.example.movietopchart.Model.MovieDetails.MovieDetails
import com.example.movietopchart.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val id = intent.getIntExtra("id", 0)
        Log.d("Test", "id: $id")

        val apiInterface = id?.let { ApiInterface.create().getMovieById(it, "bcc06d4f45d203738f635121cea37f4d") }
        apiInterface?.enqueue(object : Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                tvMovieName.text = response?.body()?.original_title
                /*tvRating.text = response?.body()?.vote_average.toString()*/

                Picasso.get().load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path).into(ivPoster)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {

            }

        })
    }
}
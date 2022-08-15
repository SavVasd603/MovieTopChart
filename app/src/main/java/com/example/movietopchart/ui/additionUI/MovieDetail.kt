package com.example.movietopchart.ui.additionUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.widget.Toast
import com.example.movietopchart.Api.ApiInterface
import com.example.movietopchart.Model.MovieDetails.Genre
import com.example.movietopchart.Model.MovieDetails.MovieDetails
import com.example.movietopchart.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        getMovieById()

        }


    fun getMovieById(){

        val id = intent.getIntExtra("id", 0)
        val apiInterface = id?.let { ApiInterface.create().getMovieById(it, "bcc06d4f45d203738f635121cea37f4d") }

        GlobalScope.launch(Dispatchers.IO) {
            apiInterface?.enqueue(object : Callback<MovieDetails>{
                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    tvMovieName.text = response?.body()?.original_title.toString()
                    tvRating.text = response?.body()?.vote_average.toString()
                    tvRelease.text = response?.body()?.release_date.toString()
                    tvOverview.text = response?.body()?.overview.toString()

                    tvGenres.text = response?.body()?.genres?.map {it.name}?.joinToString(", ")?:""

                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path).into(ivPoster)
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error $t", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
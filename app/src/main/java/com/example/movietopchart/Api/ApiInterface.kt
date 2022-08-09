package com.example.movietopchart.Api

import com.example.movietopchart.Model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/popular")
    fun getMovies(@Query ("api_key") sort : String): Call<Movie>

    companion object{

        var BASE_URL = "https://api.themoviedb.org/3/"

        fun create():ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}
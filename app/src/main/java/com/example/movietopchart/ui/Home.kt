package com.example.movietopchart.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietopchart.Api.ApiInterface
import com.example.movietopchart.Model.Movie
import com.example.movietopchart.Model.Result
import com.example.movietopchart.MovieAdapter
import com.example.movietopchart.R
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment() {

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcView.layoutManager = GridLayoutManager(context,2)
        rcView.setHasFixedSize(true)

        val apiInterface = ApiInterface.create().getMovies("bcc06d4f45d203738f635121cea37f4d")

        apiInterface.enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                adapter = MovieAdapter(response?.body()?.results as MutableList<Result>?)
                rcView.adapter = adapter

            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
               /* Toast.makeText(context, "Something goes wrong", Toast.LENGTH_LONG).show()*/
                Log.d("testlog","Failed : ${t?.message}")
            }
        })
    }
}
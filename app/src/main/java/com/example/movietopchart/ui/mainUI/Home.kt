package com.example.movietopchart.ui.mainUI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movietopchart.Api.ApiInterface
import com.example.movietopchart.Model.Movie.Movie
import com.example.movietopchart.Model.Movie.Result
import com.example.movietopchart.Adapters.MovieAdapter
import com.example.movietopchart.R
import com.example.movietopchart.ui.additionUI.MovieDetail
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        getMovies()
    }

    fun getMovies(){

        GlobalScope.launch(Dispatchers.IO) {

            val apiInterface = ApiInterface.create().getMovies("bcc06d4f45d203738f635121cea37f4d")
            apiInterface.enqueue(object : Callback<Movie>, MovieAdapter.ItemClickListener{
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    adapter = MovieAdapter(response?.body()?.results as MutableList<Result>?, this)
                    rcView.adapter = adapter

                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Toast.makeText(context, "Something goes wrong,error ${t?.message}", Toast.LENGTH_LONG).show()
                    Log.d("testlog","Failed : ${t?.message}")
                }

                override fun onItemClick(id: Int) {
                    val intent = Intent(context, MovieDetail::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            }) }
    }
}
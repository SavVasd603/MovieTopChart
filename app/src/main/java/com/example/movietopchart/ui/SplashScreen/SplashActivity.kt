package com.example.movietopchart.ui.SplashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movietopchart.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
    }
}
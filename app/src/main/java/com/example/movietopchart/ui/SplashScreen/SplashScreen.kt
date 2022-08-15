package com.example.movietopchart.ui.SplashScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.navigation.fragment.findNavController
import com.example.movietopchart.MainActivity
import com.example.movietopchart.R

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if (onBoardingScreen()){
                /*val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)*/
                findNavController().navigate(R.id.action_splashScreen_to_mainActivity)
            } else {
                findNavController().navigate(R.id.action_splashScreen_to_viewPager2)
            }
        }, 3000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    private fun onBoardingScreen() : Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished",false)
    }

}
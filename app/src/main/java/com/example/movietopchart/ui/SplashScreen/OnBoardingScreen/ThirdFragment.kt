package com.example.movietopchart.ui.SplashScreen.OnBoardingScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movietopchart.MainActivity
import com.example.movietopchart.R
import kotlinx.android.synthetic.main.fragment_first.view.*

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        view.nextbtn.setOnClickListener {
            /*val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)*/
            findNavController().navigate(R.id.action_viewPager2_to_mainActivity)
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}
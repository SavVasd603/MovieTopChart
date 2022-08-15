package com.example.movietopchart.ui.SplashScreen.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movietopchart.R
import com.example.movietopchart.ui.SplashScreen.OnBoardingScreen.FirstFragment
import com.example.movietopchart.ui.SplashScreen.OnBoardingScreen.SecondFragment
import com.example.movietopchart.ui.SplashScreen.OnBoardingScreen.ThirdFragment
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        return view
    }
}
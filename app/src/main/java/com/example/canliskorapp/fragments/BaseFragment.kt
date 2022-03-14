package com.example.canliskorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.canliskor.ui.home.HomeFragment
import com.example.canliskorapp.R

open class BaseFragment : Fragment() {

    lateinit var homeFragment: HomeFragment
    var container: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_base, container, false)



        homeFragment = HomeFragment()

        return view
    }
}
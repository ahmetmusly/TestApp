package com.canliskor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.canliskor.ui.home.HomeFragment
import com.canliskor.utils.CSComputation.getStatusBarHeight
import com.canliskor.utils.Extensions.startActivity
import com.canliskor.utils.CSGeneral.fullScreen
import com.canliskor.utils.CSKey.SELECTED_PAGE_INDEX
import com.canliskor.utils.CSFragmentManager.addFragment
import com.canliskor.utils.viewBinding
import com.example.canliskorapp.R
import com.example.canliskorapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_view.view.*
import kotlinx.android.synthetic.main.view_close_menu.*
import kotlinx.android.synthetic.main.view_close_menu.view.*


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreen()
        setContentView(binding.root)

        val sbh: Int = getStatusBarHeight()
        nav_view.setPadding(0, sbh * 2, 0, 0)
        nav_view.setNavigationItemSelectedListener {
            val bundle = Bundle().apply { putInt(SELECTED_PAGE_INDEX, it.itemId) }
            startActivity(OthersActivity::class.java, bundle = bundle)
            toggleMenu()
            true
        }

        val header = nav_view.getHeaderView(0)
        header.cl_close.setOnClickListener { toggleMenu() }

        addFragment(R.id.frameLayout, HomeFragment())
    }

    fun toggleMenu() {
        binding.apply {
            if (drawer_layout.isDrawerVisible(GravityCompat.START))
                drawer_layout.closeDrawer(GravityCompat.START)
            else
                drawer_layout.openDrawer(GravityCompat.START)
        }
    }
}
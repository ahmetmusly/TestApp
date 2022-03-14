package com.canliskor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.canliskor.ui.home.HomeFragment
import com.canliskor.ui.standing.StandingFragment
import com.canliskor.utils.CSGeneral.fullScreen
import com.canliskor.utils.CSKey.SELECTED_PAGE_INDEX
import com.canliskor.utils.CSFragmentManager.replaceFragment
import com.canliskor.utils.viewBinding
import com.example.canliskorapp.R
import com.example.canliskorapp.databinding.ActivityOthersBinding
import com.example.canliskorapp.fragments.FixtureFragment
import com.example.canliskorapp.fragments.TournamentsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.header_view_second.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


/**
 * Created by Ahmet_MUŞLUOĞLU on 4.01.2022
 */


@AndroidEntryPoint
class OthersActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOthersBinding::inflate)

    private var selectedPageIndex = 0

    private var title = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreen()

        setContentView(binding.root)

        intent.extras?.apply {
            selectedPageIndex = getInt(SELECTED_PAGE_INDEX, R.id.menu_live_score)
        }

        lifecycleScope.launch {

            withContext(Dispatchers.IO) {

                try {
                    withContext(Dispatchers.Main) {

                        replaceFragment(R.id.container_view_others, currFragment())

                        tv_title.text = title
                    }
                } catch (e: IOException) {
                    finish()
                }
            }
        }

        rl_back.setOnClickListener { onBackPressed() }
    }

    private fun currFragment(): Fragment = when (selectedPageIndex) {
        R.id.menu_live_score -> {
            title = getString(R.string.canli_skor)
            HomeFragment()
        }

//        R.id.menu_fixture -> {
//            title = getString(R.string.fikstur_ve_sonucar)
//            FixtureFragment()
//        }

        R.id.menu_score -> {
            title = getString(R.string.puan_durumu)
            StandingFragment.newInstance()
        }

//        R.id.menu_leagues -> {
//            title = getString(R.string.ligler)
//            HomeFragment()
//        }

//        R.id.menu_tournaments -> {
//            title = getString(R.string.turnuvalar)
//            TournamentsFragment()
//        }

//        R.id.menu_news -> {
//            title = getString(R.string.haberler)
//            HomeFragment()
//        }

        else -> {
            title = getString(R.string.bize_ulasin)
            StandingFragment.newInstance()
        }
    }
}
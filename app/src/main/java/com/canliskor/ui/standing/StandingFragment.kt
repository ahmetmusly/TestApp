package com.canliskor.ui.standing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.canliskor.adapters.StandingPagerAdapter
import com.canliskor.model.standings.Standings
import com.canliskor.ui.OthersActivity
import com.canliskor.ui.standing.allmatches.AllMatchesFragment
import com.canliskor.utils.CSComputation.getStatusBarHeight
import com.canliskor.utils.Extensions.gone
import com.canliskor.utils.Extensions.visible
import com.canliskor.utils.CSResource
import com.canliskor.utils.viewBinding
import com.example.canliskorapp.R
import com.example.canliskorapp.databinding.FragmentStandingBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_standing.*
import kotlinx.android.synthetic.main.header_view_second.*
import java.io.Serializable

/**
 * Created by Ahmet_MUŞLUOĞLU on 2.01.2022
 */


@AndroidEntryPoint
class StandingFragment : Fragment(R.layout.fragment_standing) {

    companion object {
        fun newInstance() = StandingFragment()
    }

    private val tabItems = arrayListOf("TÜM MAÇLAR", "İÇ SAHA MAÇLARI", "DEPLASMAN MAÇLARI")
    private val viewModel: StandingViewModel by viewModels()
    private val binding by viewBinding(FragmentStandingBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val h: Int = context?.getStatusBarHeight()!!
        cl_header_view_second.setPadding(0, h, 0, 0)

        viewModel.data.observe(viewLifecycleOwner) { response ->

            when (response) {

                is CSResource.Loading -> cl_progress_bar.visible()

                is CSResource.Success -> {
                    cl_progress_bar.gone()

                    val standings = response.data as Standings

                    refresh(standings)
                }

                is Error -> cl_progress_bar.gone()

                else -> cl_progress_bar.gone()
            }
        }

        rl_back.setOnClickListener { (activity as OthersActivity).onBackPressed() }

        tv_title.text = getString(R.string.puan_durumu)
    }

    private fun refresh(standings: Standings) {

        binding.apply {

            val bundle = Bundle().apply {
                putSerializable("standings", standings.groups[0].standings as Serializable)
            }

            val fragmentList = arrayListOf(
                AllMatchesFragment.newInstance(bundle),
                AllMatchesFragment.newInstance(bundle),
                AllMatchesFragment.newInstance(bundle),
            )

            vp_teams.adapter = StandingPagerAdapter(
                fragmentList = fragmentList,
                childFragmentManager,
                viewLifecycleOwner.lifecycle
            )

            // or ViewPager2.ORIENTATION_VERTICAL
            vp_teams.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            TabLayoutMediator(
                tabs,
                vp_teams,
                true,
                true
            ) { tab, position ->
                tab.text = tabItems[position]
            }.attach()
        }
    }
}
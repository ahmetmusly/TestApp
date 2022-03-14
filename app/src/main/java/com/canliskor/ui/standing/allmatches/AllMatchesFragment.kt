package com.canliskor.ui.standing.allmatches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canliskor.adapters.AllMatchesAdapter
import com.canliskor.model.standings.Standing
import com.canliskor.utils.Extensions
import com.canliskor.utils.Extensions.managerType
import com.canliskor.utils.viewBinding
import com.example.canliskorapp.R
import com.example.canliskorapp.databinding.FragmentAllMatchesBinding
import kotlinx.android.synthetic.main.fragment_all_matches.*

/**
 * Created by Ahmet_MUŞLUOĞLU on 2.01.2022
 */


class AllMatchesFragment : Fragment(R.layout.fragment_all_matches) {

    companion object {
        fun newInstance(data: Bundle) = AllMatchesFragment().apply {
            arguments = Bundle().apply {
                putAll(data)
            }
        }
    }

    private val viewModel: AllMatchesViewModel by viewModels()
    private val binding by viewBinding(FragmentAllMatchesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            val standingList = getSerializable("standings") as List<Standing>
            Extensions.myLog(standingList.toString())
            setupView(standingList)
        }
    }

    private fun setupView(standingList: List<Standing>) {
        binding.apply {
            rv_all_matches.apply {
                adapter = AllMatchesAdapter().apply {
                    differ.submitList(standingList)
                }
                setHasFixedSize(true)
                layoutManager = managerType(1, 1)
            }
        }
    }
}
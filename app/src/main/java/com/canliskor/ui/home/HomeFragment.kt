package com.canliskor.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.canliskor.adapters.HomeAdapter
import com.canliskor.model.Matches
import com.canliskor.model.today_matches.TodayMatches
import com.canliskor.ui.MainActivity
import com.canliskor.utils.*
import com.canliskor.utils.CSComputation.dpToPx
import com.canliskor.utils.CSComputation.getStatusBarHeight
import com.canliskor.utils.Extensions.gone
import com.canliskor.utils.Extensions.myLog
import com.canliskor.utils.Extensions.visible
import com.example.canliskorapp.R
import com.example.canliskorapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.header_view.*

/**
 * Created by Ahmet_MUŞLUOĞLU on 2.01.2022
 */


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var homeAdapter: HomeAdapter

    private var itemList: List<String>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val h: Int = context?.getStatusBarHeight()!! + context?.dpToPx(15)!!
        rl_menu_view.setPadding(context?.dpToPx(15)!!, h, 0, 0)

        setupView()

        viewModel.data.observe(viewLifecycleOwner) { response ->
            when (response) {
                is CSResource.Loading -> {
                    home_fragment_progress.visible()
                }
                is CSResource.Success -> {
                    home_fragment_progress.gone()

                    val data = response.data as TodayMatches

                    val tournaments = data.tournaments

                    homeAdapter.differ.submitList(tournaments)

                    itemList = tournaments.let { t -> t.map { it.tournamentName } }

                    itemList?.toMutableList()?.add(0, "Lig Seç")

                    itemList?.let {
                        header_view_spinner.adapter = ArrayAdapter(
                            context!!, android.R.layout.simple_spinner_dropdown_item,
                            it
                        )
                        auto_complete_textView.setAdapter(
                            ArrayAdapter(
                                context!!, android.R.layout.simple_spinner_dropdown_item,
                                it
                            )
                        )
                    }

                    header_view_spinner.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                            }
                        }
                }
                is Error -> {
                    home_fragment_progress.gone()
                }
                else -> {
                    home_fragment_progress.gone()
                }
            }
        }
    }

    private fun setupView() {
        binding.apply {

            rl_menu_view.setOnClickListener {
                (activity as MainActivity).toggleMenu()
            }

            homeAdapter = HomeAdapter().apply {
                setExtras(CSExtras().setOnclick(object : CSInterfaces.OnClickListener {
                    override fun it(data: Any?, position: Int?) {
                        myLog("HomeAdapter : $data")
//                        openDetail(data as Matches, position!!)
                    }
                }))
            }
            home_recycler_view.apply {
                adapter = homeAdapter
                setHasFixedSize(true)
                layoutManager = Extensions.managerType(1, 1)
            }
        }
    }

    fun openDetail(data: Matches?, position: Int) {
        val bundle = Bundle().apply {
            putInt("position", position)
            putSerializable("matchArgs", data)
        }
//        val action = MainFragmentDirections.actionMainFragmentToDetailActivity()
//        findNavController().navigate(action)

        findNavController().navigate(
            R.id.action_homeFragment_to_matchDetail,
            bundle
        )
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val header_view: View = inflater.inflate(R.layout.header_view, container, false)
//        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
//
//        homeRecyclerView = view.findViewById(R.id.homeRecyclerView)
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        homeRecyclerView.layoutManager = layoutManager
//
//
////        SetViewHeader(R.layout.header_view)
//
////        var home_view_layout = view.findViewById<RelativeLayout>(R.id.rl_header)
////        getViewHeader().layoutParams =
////            RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 400)
////        home_view_layout.addView(getViewHeader())
//
//        getApiResponse()
//
//        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        return view
//
//    }
//
//
//    private fun getApiResponse() {
//        var tokenString = CreateToken.GetAESToken()
//        RetrofitOperations.instance.getData(
//            ContentType = "application/x-www-form-urlencoded", Token = tokenString.toString()
//        ).enqueue(object : Callback<TodaysMatches> {
//            override fun onResponse(call: Call<TodaysMatches>, response: Response<TodaysMatches>) {
//                when (response.code()) {
//                    200 -> {
//                        val responseTemp: TodaysMatches = response.body()!!
//                        Log.d("RESPONSE", "" + responseTemp)
//
//
//                        tournamentList = responseTemp.Tournaments
//
//                        itemList = responseTemp.Tournaments.map { it.TournamentName }
//                        itemList?.toMutableList()?.add(0, "Lig Seç")
//                        arrayAdapter = ArrayAdapter(
//                            context!!, android.R.layout.simple_spinner_dropdown_item,
//                            itemList!!
//                        )
////                        spinner.adapter = arrayAdapter
////                        spinner.onItemSelectedListener = this@HomeFragment
//
//                        recyclerViewAdapter = RecyclerViewAdapter(responseTemp)
//
//                    }
//                    else -> {
//                        Toast.makeText(
//                            context,
//                            response.code().toString() + " " + response.message(),
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<TodaysMatches>, t: Throwable) {
//                //Toast.makeText(binding.root.context,t.message,Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//    override fun onItemClick(matches: Matches) {
//        val intent = Intent(context, MatchDetail::class.java)
//        requireContext().savePrefs().set("matchId", matches.Id)
//        startActivity(intent)
//    }
//
//    var lst: RecyclerViewAdapter.Listener? = null
//    fun setListener(listener: RecyclerViewAdapter.Listener) {
//        this.lst = listener
//    }
//
//    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
//        Log.e("position", position.toString())
//        if (position == 0) {
//            recyclerViewAdapter!!.setType(RecyclerViewAdapter.VIEW_TYPE_ONE)
//            recyclerViewAdapter!!.setListener(this@HomeFragment)
//            homeRecyclerView.adapter = recyclerViewAdapter
//        } else {
//            tournamentList?.get(position)?.Matches
//            val matchesList = tournamentList?.get(position)?.Matches
//            homeRecyclerView.apply {
//                val a = matchesList?.let { MatchesAdapter(it) }
//                a?.setListener(object : MatchesAdapter.Listener {
//                    override fun onItemClick(matches: Matches) {
//                        lst?.onItemClick(matches = matches)
//                    }
//                })
//                adapter = a
//                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            }
//        }
//    }
//
//    override fun onNothingSelected(p0: AdapterView<*>?) {
//        recyclerViewAdapter!!.setType(RecyclerViewAdapter.VIEW_TYPE_ONE)
//        recyclerViewAdapter!!.setListener(this@HomeFragment)
//        homeRecyclerView.adapter = recyclerViewAdapter
//    }
//


}






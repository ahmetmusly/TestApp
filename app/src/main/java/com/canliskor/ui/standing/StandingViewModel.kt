package com.canliskor.ui.standing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canliskor.model.standings.Standings
import com.canliskor.repository.CSMainRepository
import com.canliskor.utils.Extensions.myLog
import com.canliskor.utils.CSGenerateToken
import com.canliskor.utils.CSGenerateToken.getAESToken
import com.canliskor.utils.CSNetworkHelper
import com.canliskor.utils.CSResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmet_MUŞLUOĞLU on 2.01.2022
 */


@HiltViewModel
class StandingViewModel @Inject constructor(
    private val csMainRepository: CSMainRepository,
    private val csNetworkHelper: CSNetworkHelper,
) : ViewModel() {

    private val _data = MutableLiveData<CSResource<Standings>>()

    val data: LiveData<CSResource<Standings>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {

        myLog("StandingViewModel")

        _data.postValue(CSResource.Loading())

        if (csNetworkHelper.isNetworkConnected()) {

            val token = getAESToken()

            csMainRepository.getDataStandings(token = token!!, tournamentId = 52).let {

                if (it.isSuccessful)
                    _data.postValue(CSResource.Success(it.body() as Standings))
                else
                    _data.postValue(CSResource.Error(it.body().toString(), null))

            }
        } else _data.postValue(CSResource.Error("No internet connection", null))
    }
}
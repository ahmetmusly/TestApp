package com.canliskor.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canliskor.model.today_matches.TodayMatches
import com.canliskor.repository.CSMainRepository
import com.canliskor.utils.CSGenerateToken.getAESToken
import com.canliskor.utils.CSNetworkHelper
import com.canliskor.utils.CSResource
import com.canliskor.utils.Extensions.myLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ahmet_MUŞLUOĞLU on 1.01.2022
 */


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val csMainRepository: CSMainRepository,
    private val csNetworkHelper: CSNetworkHelper,
) : ViewModel() {

    private val _data = MutableLiveData<CSResource<TodayMatches>>()

    val data: LiveData<CSResource<TodayMatches>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {

        myLog("HomeViewModel")

        _data.postValue(CSResource.Loading())

        if (csNetworkHelper.isNetworkConnected()) {

            val token = getAESToken()

            csMainRepository.getDataTodayMatches(token = token!!).let {

                if (it.isSuccessful)
                    _data.postValue(CSResource.Success(it.body() as TodayMatches))
                else
                    _data.postValue(CSResource.Error(it.body().toString(), null))

            }
        } else _data.postValue(CSResource.Error("No internet connection", null))
    }
}
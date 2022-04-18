package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.LeaderBoardResponseData
import com.mexcelle.thoughtifydemo.model.LeaguesResponseData
import com.mexcelle.thoughtifydemo.model.NowAndThenResponseData
import com.mexcelle.thoughtifydemo.repository.NowAndThenRepository
import com.mexcelle.thoughtifydemo.repository.PurchaseFlairFragmentRepository

class PurchaseFlairViewModel : ViewModel() {

    var leaderBoardResponseData: MutableLiveData<LeaderBoardResponseData?>? =
        MutableLiveData<LeaderBoardResponseData?>()


    var leaguesResponseData: MutableLiveData<LeaderBoardResponseData?>? =
        MutableLiveData<LeaderBoardResponseData?>()


    fun getLeaderBoard(context: Context): MutableLiveData<LeaderBoardResponseData?>? {

        leaderBoardResponseData = PurchaseFlairFragmentRepository.getLeaderBoardDetails(context)
        return leaderBoardResponseData

    }

    fun getLeaguesData(context: Context,id:String): MutableLiveData<LeaderBoardResponseData?>? {

        leaguesResponseData = PurchaseFlairFragmentRepository.getLeaguesDetails(context,id)
        return leaguesResponseData

    }


}
package com.fleetmaster.fleetmaster.find_tool_list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Norbert on 23.06.2017.
 */
class MainViewModel: ViewModel() {


    val liveDataToolList = MutableLiveData<List<Tool>>()

    fun getObservableToolList(): LiveData<List<Tool>> {
        return liveDataToolList
    }


    fun loadTools() {

    }

}
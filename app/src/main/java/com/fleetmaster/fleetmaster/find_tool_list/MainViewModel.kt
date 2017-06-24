package com.fleetmaster.fleetmaster.find_tool_list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.fleetmaster.fleetmaster.find_tool_list.network.Position
import com.fleetmaster.fleetmaster.find_tool_list.network.ToolRepository
import com.fleetmaster.fleetmaster.find_tool_list.recyclerView.Tool

/**
 * Created by Norbert on 23.06.2017.
 */
class MainViewModel: ViewModel() {


    private val liveDataToolList = MutableLiveData<List<Tool>>()
    private val liveDataShowPosition = MutableLiveData<Position>()



    private val toolRepository = ToolRepository()

    fun getObservableToolList(): LiveData<List<Tool>> {
        return toolRepository.getToolList()
    }

    fun getObservableShowPosition() = liveDataShowPosition


    fun onItemClicked(id: Int) {

        toolRepository.getItemPosition(id, {position -> liveDataShowPosition.value = position})
    }


}
package com.fleetmaster.fleetmaster.find_tool_list.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.fleetmaster.fleetmaster.find_tool_list.recyclerView.Tool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



/**
 * Created by Norbert on 23.06.2017.
 */
class ToolRepository {

    val service: FleetMasterService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://martinshare.com/api/van.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(FleetMasterService::class.java)

    }

    fun getToolList(): LiveData<List<Tool>> {
        val liveData = MutableLiveData<List<Tool>>()

        service.getToolList().enqueue(object: Callback<List<Tool>>{
            override fun onFailure(call: Call<List<Tool>>?, t: Throwable?) {
                //Cry silently
            }

            override fun onResponse(call: Call<List<Tool>>?, response: Response<List<Tool>>?) {
                liveData.value = response?.body()
            }
        })


        return liveData

    }

    fun getItemPosition(id: Int, func: (Position) -> Unit) {

        service.getPosition(id).enqueue(object : Callback<Position> {
            override fun onFailure(call: Call<Position>?, t: Throwable?) {
                //Cry silently
                Log.d("lol", t.toString())
            }

            override fun onResponse(call: Call<Position>?, response: Response<Position>?) {
                Log.d("lol","succ")
                response?.let {
                    it.body()?.let {
                        func(it)
                    }
                }
            }
        })


    }
}
package com.fleetmaster.fleetmaster.find_tool_list.network

import com.fleetmaster.fleetmaster.find_tool_list.recyclerView.Tool
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Norbert on 23.06.2017.
 */
interface FleetMasterService {

    @GET("warenliste")
    fun getToolList(): Call<List<Tool>>

    @GET("finditem/{id}")
    fun getPosition(@Path("id")id: Int): Call<Position>
}
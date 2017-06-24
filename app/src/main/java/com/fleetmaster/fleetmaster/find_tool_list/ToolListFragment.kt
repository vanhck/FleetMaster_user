package com.fleetmaster.fleetmaster.find_tool_list


import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fleetmaster.fleetmaster.R
import com.fleetmaster.fleetmaster.find_tool_list.recyclerView.ItemAdapter
import com.fleetmaster.fleetmaster.find_tool_map.ToolMapsActivity


/**
 * A simple [Fragment] subclass.
 */
class ToolListFragment : LifecycleFragment() {


    val viewModel: MainViewModel by lazy {ViewModelProviders.of(this).get(MainViewModel::class.java)}


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater!!.inflate(R.layout.fragment_tool_list, container, false)


        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ItemAdapter(mutableListOf())
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener {
            id -> viewModel.onItemClicked(id)
        }


        viewModel.getObservableShowPosition().observe(this, Observer {
            it?.let {
                ToolMapsActivity.startActivity(it.lat, it.lon, context)
            }
        })

        viewModel.getObservableToolList().observe(this, Observer {
            it?.let {
                adapter.setData(it)
            }
        })

        return view
    }


}
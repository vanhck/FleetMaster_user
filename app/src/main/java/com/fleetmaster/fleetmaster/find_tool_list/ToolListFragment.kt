package com.fleetmaster.fleetmaster.find_tool_list


import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fleetmaster.fleetmaster.R
import com.fleetmaster.fleetmaster.find_tool_list.recyclerView.ItemAdapter
import com.fleetmaster.fleetmaster.find_tool_map.ToolMapsActivity
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.fleetmaster.fleetmaster.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tool_list.*


/**
 * A simple [Fragment] subclass.
 */
class ToolListFragment : LifecycleFragment() {


    val viewModel: MainViewModel by lazy {ViewModelProviders.of(this).get(MainViewModel::class.java)}


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater!!.inflate(R.layout.fragment_tool_list, container, false)



/*

        activity?.let {
            it as MainActivity
            it.setSupportActionBar() = Toolbar(context)

        }
*/
        activity?.toolbar2?.removeAllViews()
        val v = LayoutInflater.from(context)
                .inflate(R.layout.action_bar, null, false)
        activity?.toolbar2?.addView(v)

        val searchText = v.findViewById(R.id.search_text) as EditText

        searchText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    viewModel.restrictToStartsWith(s.toString().toLowerCase())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ItemAdapter(mutableListOf())
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
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
                spinner.visibility = View.GONE
            }
        })

        return view
    }


}
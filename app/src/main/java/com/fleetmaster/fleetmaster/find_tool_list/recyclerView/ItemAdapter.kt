package com.fleetmaster.fleetmaster.find_tool_list.recyclerView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fleetmaster.fleetmaster.R

/**
 * Created by Norbert on 23.06.2017.
 */
class ItemAdapter(listData: List<Tool>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var listData = listData
    set(it) {
        listData = it
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById(R.id.tool_name) as TextView

        fun bindText(name: String) {
            textView.text = name
        }
    }

    override fun getItemCount() = listData.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.tool_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindText(listData[position].name)
    }


}
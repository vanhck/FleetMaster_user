package com.fleetmaster.fleetmaster.register

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.fleetmaster.fleetmaster.R

/**
 * Created by Norbert on 24.06.2017.
 */
class PersonAdapter(private var dataList: List<Person>): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {


    var listener: ((Person) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textView = itemView.findViewById(R.id.textView2) as TextView
        val button = itemView.findViewById(R.id.button2) as Button


        init {
            button.setOnClickListener {
                listener?.let {
                    it(dataList[adapterPosition])
                }
            }
        }

    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.textView.text = dataList[position].name

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.person_item, parent, false)
        return ViewHolder(v)
    }

    fun setData(data: List<Person>) {
        dataList = data
        notifyDataSetChanged()
    }

    fun setOnPersonClickListener(listener: (Person) -> Unit) {
        this.listener = listener
    }
}
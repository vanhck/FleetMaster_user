package com.fleetmaster.fleetmaster.find_tool_list.recyclerView

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.fleetmaster.fleetmaster.R

/**
 * Created by Norbert on 23.06.2017.
 */
class ItemAdapter(private var listData: List<Tool>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var listener: ((Int) -> Unit)? = null

    fun setData(data: List<Tool>) {
        this.listData = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(func: (Int) -> Unit) {
        this.listener = func
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById(R.id.tool_name) as TextView
        private val imageButton = itemView.findViewById(R.id.imageButton) as ImageButton
        private var isAvailable = false

        init {
            itemView.setOnClickListener {
                if(isAvailable) {
                    launchFindToolActivity(adapterPosition)
                } else {
                    Toast.makeText(itemView.context, "Das Objekt kann nicht abgeholt werden", Toast.LENGTH_SHORT).show()
                }
            }

            imageButton.setOnClickListener {
                if(isAvailable) {
                    launchFindToolActivity(adapterPosition)
                } else {
                    Toast.makeText(itemView.context, "Das Objekt kann nicht abgeholt werden", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun bindText(name: String) {
            textView.text = name
        }

        fun bindAvailable(available: Boolean){
            this.isAvailable = available
            if(available) {
                imageButton.setImageResource(R.drawable.ic_check_black_24dp)
        //        imageButton.setColorFilter(R.color.green,android.graphics.PorterDuff.Mode.MULTIPLY)
              //  itemView.setBackgroundColor(Color.parseColor("#81C784"))
                DrawableCompat.setTint(imageButton.drawable, ContextCompat.getColor(imageButton.context, R.color.green));


            } else {
                imageButton.setImageResource(R.drawable.ic_close_black_24dp)
                DrawableCompat.setTint(imageButton.drawable, ContextCompat.getColor(imageButton.context, R.color.red));


                //    imageButton.setColorFilter(R.color.red,android.graphics.PorterDuff.Mode.MULTIPLY)
          //      itemView.setBackgroundColor(Color.parseColor("#E57373"))
            }
        }

        private fun launchFindToolActivity(pos: Int) {
            val id = listData[pos].id
            listener?.let {
                it(id)
            }
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
        holder?.bindAvailable(listData[position].available)
    }

}
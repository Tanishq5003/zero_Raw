package com.example.zeroraw

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (val itemlist: ArrayList<recylerarray>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvAdress: TextView = itemView.findViewById(R.id.address)
        val tvPrice: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvAdress.text = itemlist[position].address
        holder.tvPrice.text = itemlist[position].price
    }

}
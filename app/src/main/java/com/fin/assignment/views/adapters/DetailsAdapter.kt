package com.fin.assignment.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fin.assignment.R
import com.fin.assignment.model.response.DetailsResponseItem

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.MyViewHolder>() {
    lateinit var detailsList: ArrayList<DetailsResponseItem>

    fun setData(list: ArrayList<DetailsResponseItem>) {
        detailsList = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapter.MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.details_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.MyViewHolder, position: Int) {
        val result =  detailsList[position]
        result.let {
            holder.email.text =result.email
            holder.mobileNumber.text = result.mobileNo.toString()
        }
    }

    override fun getItemCount()= detailsList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var email:TextView = itemView.findViewById(R.id.emailTV)
        var mobileNumber : TextView = itemView.findViewById(R.id.numberTV)
    }
}
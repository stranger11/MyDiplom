package com.example.mydiplom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiplom.data.DayResults
import kotlinx.android.synthetic.main.custom_row.view.*

class DayResultsAdapter: RecyclerView.Adapter<DayResultsAdapter.MyViewHolder>() {

    private var resultList = emptyList<DayResults>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = resultList[position]
        holder.itemView.result_date.text = currentItem.date
        holder.itemView.result_calories.text = currentItem.dayCalories.toString()
        holder.itemView.result_water.text = currentItem.dayWater.toString()
        holder.itemView.result_pushups.text = currentItem.dayPushUps.toString()
        holder.itemView.result_squats.text = currentItem.daySquats.toString()
        holder.itemView.result_press.text = currentItem.dayPress.toString()
        holder.itemView.result_run.text = currentItem.dayRun.toString()
    }

    fun setData(dayResult: List<DayResults>) {
        this.resultList = dayResult
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return resultList.size
    }
}
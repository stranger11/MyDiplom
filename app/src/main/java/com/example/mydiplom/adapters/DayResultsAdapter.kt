package com.example.mydiplom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiplom.R
import com.example.mydiplom.data.DayResults
import com.example.mydiplom.databinding.CustomRowBinding



class DayResultsAdapter: RecyclerView.Adapter<DayResultsAdapter.MyViewHolder>() {

    private var resultList = emptyList<DayResults>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = CustomRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = resultList[position]
        with(holder) {
            binding.resultDate.text = currentItem.date
            binding.resultCalories.text = currentItem.dayCalories.toString()
            binding.resultWater.text = currentItem.dayWater.toString()
            binding.resultPushups.text = currentItem.dayPushUps.toString()
            binding.resultSquats.text = currentItem.daySquats.toString()
            binding.resultPress.text = currentItem.dayPress.toString()
            binding.resultRun.text = currentItem.dayRun.toString()
        }
    }

    fun setData(dayResult: List<DayResults>) {
        this.resultList = dayResult
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return resultList.size
    }
}
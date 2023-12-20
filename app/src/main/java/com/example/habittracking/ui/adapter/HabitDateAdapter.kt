package com.example.habittracking.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracking.data.entity.HabitDate
import com.example.habittracking.databinding.HabitsDateCardviewBinding

class HabitDateAdapter(private val mContext:Context,private val datesList:List<HabitDate>) :
RecyclerView.Adapter<HabitDateAdapter.HabitDayVH>(){

    inner class HabitDayVH(binding:HabitsDateCardviewBinding): RecyclerView.ViewHolder(binding.root){
        var binding:HabitsDateCardviewBinding
        init {
            this.binding= binding

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitDayVH {
        val layoutInf= LayoutInflater.from(mContext)
        val binding= HabitsDateCardviewBinding.inflate(layoutInf,parent,false)
        return HabitDayVH(binding)
    }

    override fun getItemCount(): Int {
        return datesList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HabitDayVH, position: Int) {
        val date= datesList.get(position)
        val h= holder.binding
        h.dayName.setText(date.date.dayOfMonth.toString())
        h.dayNumber.setText(date.date.dayOfWeek.toString().substring(0,3))

    }


}
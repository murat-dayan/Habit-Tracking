package com.example.habittracking.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracking.R
import com.example.habittracking.data.entity.Habit
import com.example.habittracking.databinding.HabitCardviewBinding
import com.example.habittracking.ui.fragment.MainPageFragment
import com.example.habittracking.ui.fragment.MainPageFragmentDirections
import com.example.habittracking.util.doSwitch

class HabitAdapter(private val mContext:Context,private val habitsList:List<Habit>):
        RecyclerView.Adapter<HabitAdapter.HabitsVH>()
{
    inner class HabitsVH(binding: HabitCardviewBinding): RecyclerView.ViewHolder(binding.root){
        var binding: HabitCardviewBinding

        init {
            this.binding=binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsVH {
        val layoutInf= LayoutInflater.from(mContext)
        val binding= HabitCardviewBinding.inflate(layoutInf,parent,false)
        return HabitsVH(binding)
    }

    override fun getItemCount(): Int {
        return habitsList.size
    }

    override fun onBindViewHolder(holder: HabitsVH, position: Int) {
        val habit= habitsList.get(position)

        holder.binding.cardviewHabitName.text= habit.habitName
        holder.binding.habitCardview.setOnClickListener {
            val switch = MainPageFragmentDirections.switchToHabitCreateFragment(habit)
            Navigation.findNavController(holder.binding.root).navigate(switch)
        }
    }
}
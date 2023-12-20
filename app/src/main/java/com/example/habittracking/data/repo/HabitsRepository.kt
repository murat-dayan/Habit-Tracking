package com.example.habittracking.data.repo

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.habittracking.R
import com.example.habittracking.data.entity.HabitDate
import java.time.LocalDate
import java.time.ZoneId

class HabitsRepository() {

    private val dateList= mutableListOf<HabitDate>()


    @RequiresApi(Build.VERSION_CODES.O)
    fun createHabitList() : MutableList<HabitDate>{
        val currentDate= LocalDate.now(ZoneId.systemDefault())
        for (i in 1..20){
            dateList.add(HabitDate(currentDate.minusDays(i.toLong()-1)))
        }
        return dateList
    }


}
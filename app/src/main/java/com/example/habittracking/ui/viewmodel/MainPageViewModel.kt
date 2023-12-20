package com.example.habittracking.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittracking.data.entity.Habit
import com.example.habittracking.data.entity.HabitDate
import com.example.habittracking.data.repo.HabitsDaoRepository
import com.example.habittracking.data.repo.HabitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(var habitsDaoRepo: HabitsDaoRepository): ViewModel() {

    var habitsList = MutableLiveData<List<Habit>>()
    val hRepo = HabitsRepository()

    init {
        loadHabits()
        habitsList = habitsDaoRepo.bringHabits()
    }

    fun loadHabits(){
        habitsDaoRepo.getAllPersons()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHabitsDateList(): MutableList<HabitDate>{
        return hRepo.createHabitList()
    }


}
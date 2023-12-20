package com.example.habittracking.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.habittracking.data.entity.Habit
import com.example.habittracking.room.HabitsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitsDaoRepository(var hDao: HabitsDao) {

    var habitsList: MutableLiveData<List<Habit>>

    init {
        habitsList = MutableLiveData()
    }

    fun bringHabits(): MutableLiveData<List<Habit>> {
        return habitsList
    }

    fun getAllPersons() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            habitsList.value = hDao.allHabits()
        }
    }

    fun saveHabit(
        habit_name: String,
        habit_question: String? = null,
        habit_frequency: String? = null,
        habit_remanding: String? = null,
        habit_note:String?=null,
        habit_date:String?=null
    ) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newHabit = Habit(0, habit_name, habit_question, habit_frequency,habit_remanding,habit_note,habit_date)
            hDao.addHabit(newHabit)
        }
    }

    fun updateHabit(
        habit_id:Int,
        habit_name: String,
        habit_question: String? = null,
        habit_frequency: String? = null,
        habit_remanding: String? = null,
        habit_note:String?=null,
        habit_date:String?=null
    ) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updatedHabit = Habit(habit_id,habit_name, habit_question, habit_frequency,habit_remanding,habit_note,habit_date)
            hDao.updateHabit(updatedHabit)
        }
    }
}
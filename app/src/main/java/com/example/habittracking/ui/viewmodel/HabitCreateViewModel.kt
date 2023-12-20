package com.example.habittracking.ui.viewmodel

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.habittracking.data.entity.Habit
import com.example.habittracking.data.entity.HabitDate
import com.example.habittracking.data.repo.HabitsDaoRepository
import com.example.habittracking.data.repo.HabitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitCreateViewModel @Inject constructor(var hDao: HabitsDaoRepository): ViewModel() {

    val hRepo = HabitsRepository()

    fun saveButton(
        habit_name: String,
        habit_question: String? = null,
        habit_frequency: String? = null,
        habit_remanding: String? = null,
        habit_note:String?=null,
        habit_date:String?=null
    ) {
        hDao.saveHabit(habit_name,habit_question,habit_frequency,habit_remanding,habit_note,habit_date)
    }

    fun updateWithSaveButton(
        habit_id:Int,
        habit_name: String,
        habit_question: String? = null,
        habit_frequency: String? = null,
        habit_remanding: String? = null,
        habit_note:String?=null,
        habit_date:String?=null
    ) {
        hDao.updateHabit(habit_id,habit_name,habit_question,habit_frequency,habit_remanding,habit_note,habit_date)
    }



}
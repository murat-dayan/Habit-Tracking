package com.example.habittracking.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habittracking.data.entity.Habit

@Dao
interface HabitsDao {

    @Query("SELECT*FROM habits")
    suspend fun allHabits(): List<Habit>

    @Insert
    suspend fun addHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)
}
package com.example.habittracking.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habittracking.data.entity.Habit

@Database(entities = [Habit::class], version = 1)
abstract class RmDatabase  : RoomDatabase(){

    abstract fun getHabitsDao() : HabitsDao

}
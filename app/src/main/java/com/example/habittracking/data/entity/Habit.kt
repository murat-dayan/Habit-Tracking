package com.example.habittracking.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable


@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("habit_id")  var habitId:Int,
    @ColumnInfo("habit_name")  var habitName: String,
    @ColumnInfo("habit_question")  var habitQuestion: String?=null,
    @ColumnInfo("habit_frequency")  var habitFrequency: String?=null,
    @ColumnInfo("habit_remanding")  var habitRemanding:String?=null,
    @ColumnInfo("habit_note")  var habitNote: String?=null,
    @ColumnInfo("habit_date")  var habitDate:String?=null
): Serializable{

}

package com.example.habittracking.util

import android.view.View
import androidx.navigation.Navigation

fun Navigation.doSwitch(it:View,id:Int){
    findNavController(it).navigate(id)
}
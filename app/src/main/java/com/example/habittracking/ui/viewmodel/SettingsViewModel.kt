package com.example.habittracking.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.habittracking.data.repo.HabitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(var habitsRepo: HabitsRepository) : ViewModel() {


}
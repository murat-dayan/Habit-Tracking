package com.example.habittracking.ui.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.habittracking.R
import com.example.habittracking.databinding.FragmentHabitCreateBinding
import com.example.habittracking.ui.viewmodel.HabitCreateViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar

@AndroidEntryPoint
class HabitCreateFragment : Fragment() {
    private lateinit var binding: FragmentHabitCreateBinding
    private lateinit var viewModel: HabitCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_habit_create, container, false)

        val bundle: HabitCreateFragmentArgs? by navArgs()
        val receivedHabit = bundle?.habit

        if (receivedHabit != null) {
            binding.editTextHabitName.setText(receivedHabit.habitName)
            binding.editTextQuestion.setText(receivedHabit.habitQuestion)
            binding.editTextFregquency.setText(receivedHabit.habitFrequency)
            binding.editTextReminding.setText(receivedHabit.habitRemanding)
            binding.editTextNotes.setText(receivedHabit.habitNote)
        }

        binding.toolbarHabitPage.title = "Create Habit"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHabitPage)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarHabitPage.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.editTextFregquency.setText("Every Day")
        binding.editTextFregquency.isFocusable = false
        binding.editTextFregquency.isFocusableInTouchMode = false

        binding.editTextFregquency.setOnClickListener {
            val popUpMenu = PopupMenu(requireContext(), binding.editTextFregquency)
            popUpMenu.menuInflater.inflate(R.menu.reminder_menu, popUpMenu.menu)

            popUpMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_ten_day_month -> {
                        binding.editTextFregquency.setText(it.title)
                        true
                    }

                    R.id.action_every_day -> {
                        binding.editTextFregquency.setText(it.title)
                        true
                    }

                    R.id.action_three_day -> {
                        binding.editTextFregquency.setText(it.title)
                        true
                    }

                    else -> false
                }
            }

            popUpMenu.show()

        }
        binding.editTextReminding.setText("Off")
        binding.editTextReminding.isFocusable = false
        binding.editTextReminding.isFocusableInTouchMode = false

        binding.editTextReminding.setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                val editableText = Editable.Factory.getInstance()
                    .newEditable(SimpleDateFormat("HH:mm").format(cal.time))
                binding.editTextReminding.text = editableText
            }
            TimePickerDialog(
                requireContext(),
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        binding.buttonSave.setOnClickListener {
            val habitName = binding.editTextHabitName.text.toString()
            val habitQuestion = binding.editTextQuestion.text.toString()
            val habitFrequency = binding.editTextFregquency.text.toString()
            val habitRemanding = binding.editTextReminding.text.toString()
            val habitNote = binding.editTextNotes.text.toString()
            val habitDate = binding.editTextNotes.text.toString()

            if (receivedHabit != null) {
                viewModel.updateWithSaveButton(
                    receivedHabit.habitId,
                    habitName,
                    habitQuestion,
                    habitFrequency,
                    habitRemanding,
                    habitNote,
                    habitDate
                )
            } else {
                viewModel.saveButton(
                    habitName,
                    habitQuestion,
                    habitFrequency,
                    habitRemanding,
                    habitNote,
                    habitDate
                )
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempviewmodel: HabitCreateViewModel by viewModels()
        viewModel = tempviewmodel
    }


}
package com.example.habittracking.ui.fragment

import android.graphics.Color
import android.icu.text.DateFormatSymbols
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.habittracking.R
import com.example.habittracking.data.entity.Habit
import com.example.habittracking.data.entity.HabitDate
import com.example.habittracking.databinding.FragmentMainPageBinding
import com.example.habittracking.ui.adapter.HabitAdapter
import com.example.habittracking.ui.adapter.HabitDateAdapter
import com.example.habittracking.ui.viewmodel.MainPageViewModel
import com.example.habittracking.util.doSwitch
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var viewmodel: MainPageViewModel
    private lateinit var habitDateAdapter: HabitDateAdapter
    private var dateList= mutableListOf<HabitDate>()
    private lateinit var habitAdapter: HabitAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main_page,container,false)

        binding.toolbarMainPage.setTitleTextColor(Color.WHITE)
        binding.toolbarMainPage.title = "Habits"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMainPage)



        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                when (menuItem.itemId) {
                    R.id.action_about -> {
                        Navigation.doSwitch(binding.toolbarMainPage,R.id.switchToAboutFragment)
                        return true
                    }

                    R.id.action_setting -> {
                        Navigation.doSwitch(binding.toolbarMainPage,R.id.switchToSettingsFragment)
                        return true
                    }

                    R.id.action_add -> {
                        val switch = MainPageFragmentDirections.switchToHabitCreateFragment(null)
                        Navigation.findNavController(binding.toolbarMainPage).navigate(switch)
                        return true
                    }

                    R.id.action_help_faq -> {
                        return true
                    }

                    else -> return false
                }

            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)



        binding.rvDates.setHasFixedSize(true)
        val layoutMan= StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
        binding.rvDates.layoutManager= layoutMan


        dateList=viewmodel.getHabitsDateList()
        habitDateAdapter= HabitDateAdapter(requireContext(),dateList)
        binding.rvDates.adapter=habitDateAdapter

        binding.rvHabits.setHasFixedSize(true)
        binding.rvHabits.layoutManager = LinearLayoutManager(requireContext())


        viewmodel.habitsList.observe(viewLifecycleOwner){
            habitAdapter= HabitAdapter(requireContext(),it)
            binding.rvHabits.adapter= habitAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:MainPageViewModel by viewModels()
        viewmodel= tempViewModel
    }

    override fun onResume() {
        super.onResume()

        viewmodel.loadHabits()
    }


}
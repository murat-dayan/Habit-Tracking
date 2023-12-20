package com.example.habittracking.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.habittracking.R
import com.example.habittracking.databinding.FragmentAboutBinding
import com.example.habittracking.ui.viewmodel.AboutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private lateinit var viewModel: AboutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_about,container,false)

        binding.toolbarAboutPage.setTitleTextColor(Color.WHITE)
        binding.toolbarAboutPage.title="About"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAboutPage)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarAboutPage.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }


        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempviewmodel: AboutViewModel by viewModels()
        viewModel= tempviewmodel
    }
}
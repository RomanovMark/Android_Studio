package com.example.weatherappwear

import android.app.Activity
import android.os.Bundle
import com.example.weatherappwear.databinding.ActivityMainWearBinding

class MainActivityWear : Activity() {

    private lateinit var binding: ActivityMainWearBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainWearBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
package com.okifirsyah.bimbellinear.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okifirsyah.bimbellinear.R

class MainActivity : AppCompatActivity() {

//    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel.getThemeSettings().observe(this
//        ) { isDarkModeActive: Boolean ->
//            if (isDarkModeActive) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }
    }
}
package com.bobolingo.bobolingoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.bobolingo.bobolingoapp.presentation.screens.SliderDisplay
import com.bobolingo.bobolingoapp.ui.theme.BackgroundLight
import com.bobolingo.bobolingoapp.ui.theme.BobolingoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BobolingoAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundLight)
                ) {
                    SliderDisplay()
                }
            }
        }
    }
}
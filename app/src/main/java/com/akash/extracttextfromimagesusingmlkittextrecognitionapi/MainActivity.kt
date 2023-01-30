package com.akash.extracttextfromimagesusingmlkittextrecognitionapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.akash.extracttextfromimagesusingmlkittextrecognitionapi.presentation.MainScreen
import com.akash.extracttextfromimagesusingmlkittextrecognitionapi.ui.theme.ExtractTextFromImagesUsingMLKitTextRecognitionAPITheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExtractTextFromImagesUsingMLKitTextRecognitionAPITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


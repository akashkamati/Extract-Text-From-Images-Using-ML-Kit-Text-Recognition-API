package com.akash.extracttextfromimagesusingmlkittextrecognitionapi.presentation

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.extracttextfromimagesusingmlkittextrecognitionapi.domain.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    private var _extractedText = MutableStateFlow("")
    val extractedText = _extractedText.asStateFlow()


    fun getTextFromCapturedImageBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            mainRepo
                .getTextFromCapturedImage(bitmap)
                .collect{ text ->
                    _extractedText.value = text
                }
        }
    }


    fun getTextFromSelectedImage(uri: Uri){
        viewModelScope.launch {
            mainRepo
                .getTextFromSelectedImage(uri)
                .collect{ text ->
                    _extractedText.value = text
                }
        }
    }

    fun copyTextToClipboard(){
        viewModelScope.launch {
            mainRepo.copyTextToClipboard(_extractedText.value)
        }
    }



}
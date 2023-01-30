package com.akash.extracttextfromimagesusingmlkittextrecognitionapi.domain.repo

import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface MainRepo {


    fun getTextFromCapturedImage(bitmap: Bitmap):Flow<String>

    fun getTextFromSelectedImage(uri: Uri):Flow<String>

    fun copyTextToClipboard(text:String)

}
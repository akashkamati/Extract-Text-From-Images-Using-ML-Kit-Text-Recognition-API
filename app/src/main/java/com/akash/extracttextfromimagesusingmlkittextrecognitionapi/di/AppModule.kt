package com.akash.extracttextfromimagesusingmlkittextrecognitionapi.di

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import com.akash.extracttextfromimagesusingmlkittextrecognitionapi.data.repo.MainRepoImpl
import com.akash.extracttextfromimagesusingmlkittextrecognitionapi.domain.repo.MainRepo
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application) : Context{
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideTextRecognizer(): TextRecognizer {
        return TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }

    @Provides
    @Singleton
    fun provideClipboardManager(context: Context):ClipboardManager{
        return context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

    }


    @Provides
    @Singleton
    fun provideMainRepo(recognizer: TextRecognizer,context: Context,clipboardManager: ClipboardManager):MainRepo{
        return MainRepoImpl(
            recognizer = recognizer,
            context = context,
            clipboardManager = clipboardManager
        )
    }


}
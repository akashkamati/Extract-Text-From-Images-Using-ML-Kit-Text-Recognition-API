package com.akash.extracttextfromimagesusingmlkittextrecognitionapi.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = GetContent(),
        onResult = viewModel::getTextFromSelectedImage
    )

    val cameraLauncherIntent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = viewModel::getTextFromCapturedImageBitmap
    )

    val extractedText = viewModel.extractedText.collectAsState().value



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Card(
            elevation = 7.dp
        ) {
            LazyColumn(modifier = Modifier.fillMaxHeight(0.7f).fillMaxWidth()) {
                item {
                    SelectionContainer {
                        Text(text = extractedText, modifier = Modifier.padding(vertical = 3.dp, horizontal = 7.dp))
                    }
                }
            }
        }




        Button(
            onClick = viewModel::copyTextToClipboard,
            enabled = extractedText.isNotBlank()
        ) {
            Text(text = "Copy entire text")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            Button(onClick = {
                cameraLauncherIntent.launch()
            }) {
                Text(text = "Start camera")
            }

            Button(onClick = { galleryLauncher.launch("image/*") }) {
                Text(text = "Choose from gallery")
            }


        }


    }

}
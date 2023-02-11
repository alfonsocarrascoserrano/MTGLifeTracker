package com.example.mtglifetracker.views

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mtglifetracker.Data
import com.example.mtglifetracker.viewModel.CardViewModel

val tableSize = mutableStateOf(value = 4)

@Composable
fun start(navController: NavController) {
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scale(2f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Table size:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(modifier = Modifier.size(30.dp,30.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                if(tableSize.value>2) tableSize.value-- }) {
                Text("-")
            }
            Text(modifier = Modifier.padding(10.dp), text = tableSize.value.toString())
            Button(modifier = Modifier.size(30.dp,30.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                if(tableSize.value<6) tableSize.value++ }) {
                Text(text = "+")
            }
        }
        Button(modifier = Modifier.fillMaxWidth(0.4f),
            onClick = {
            Data.lifeList = mutableListOf(40,40,40,40,40,40)
            navController.navigate("table/"+ tableSize.value.toString()) }) {
            Text(text = "Start")
        }
        //Spacer(modifier = Modifier.size(30.dp))
        Button(modifier = Modifier.fillMaxWidth(0.4f),
            onClick = {
            navController.navigate("cards") }) {
            Text(text = "Search Cards")
        }
        Button(modifier = Modifier.fillMaxWidth(0.4f),
            onClick = {
            activity?.finish() }) {
            Text(text = "Exit")
        }
    }
}
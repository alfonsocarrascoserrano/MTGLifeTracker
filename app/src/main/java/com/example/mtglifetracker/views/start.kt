package com.example.mtglifetracker.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import com.example.mtglifetracker.Data

@Composable
fun start(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scale(2f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Create table with:")
        Button(onClick = {
            Data.lifeList = mutableListOf(40,40,40,40,40,40)
            navController.navigate("table/2") }) {
            Text(text = "2 Players")
        }
        Button(onClick = {
            Data.lifeList = mutableListOf(40,40,40,40,40,40)
            navController.navigate("table/3") }) {
            Text(text = "3 Players")
        }
        Button(onClick = {
            Data.lifeList = mutableListOf(40,40,40,40,40,40)
            navController.navigate("table/4") }) {
            Text(text = "4 Players")
        }
        Button(onClick = {
            Data.lifeList = mutableListOf(40,40,40,40,40,40)
            navController.navigate("table/5") }) {
            Text(text = "5 Players")
        }
        Button(onClick = {
            Data.lifeList = mutableListOf(40,40,40,40,40,40)
            navController.navigate("table/6") }) {
            Text(text = "6 Players")
        }
    }
}